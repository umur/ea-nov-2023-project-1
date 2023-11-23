package com.alumni.userservice.service.impl;

import com.alumni.userservice.entity.AuditAction;
import com.alumni.userservice.entity.Role;
import com.alumni.userservice.entity.User;
import com.alumni.userservice.exceptions.GlobalAPIException;
import com.alumni.userservice.payload.LoginDto;
import com.alumni.userservice.payload.UserMinimalDto;
import com.alumni.userservice.repository.RoleRepository;
import com.alumni.userservice.repository.UserRepository;
import com.alumni.userservice.security.JwtTokenProvider;
import com.alumni.userservice.service.AuditLogService;
import com.alumni.userservice.service.AuthService;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Implemented Login logic using DAO Authentication provider and JWT Authentication Token
     */

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    private final AuditLogService auditLogService;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepo, RoleRepository roleRepo,
                           PasswordEncoder passwordEncoder,
                           AuditLogService auditLogService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.auditLogService = auditLogService;
    }

    @Override
    public String login(LoginDto loginDto) {

        var u = new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(u);


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        auditLogService.save(loginDto.getUsernameOrEmail(), AuditAction.LOGIN);

        return token;
    }

    private void processLogin(String username) {
        Optional<User> userOptional = userRepo.findByUsernameOrEmail(username, username);
        userOptional.ifPresent(
                user -> {
                    if (user.isAccountNonLocked()) {
                        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
                        userRepo.save(user);

                        if (user.getFailedLoginAttempts() <= 5) {
                            return;
                        }
                        user.setAccountNonLocked(false);
                        userRepo.save(user);
                        throw new RuntimeException("Please reset your password");
                    }
                }
        );
    }

    @Override
    public String register(UserMinimalDto registerDto) {
        // Checking for existing username already exists in the database
        if (userRepo.existsByUsername(registerDto.getUsername())) {
            throw new GlobalAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        // Checking for email already exists in the database
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new GlobalAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        // Whenever, user registers, we assign a default role ROLE_USER
        Role userRole = roleRepo.findByName("ROLE_USER").orElseThrow(RuntimeException::new);
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);

        return "User registered successfully!";
    }

    @EventListener
    public void authenticationListener(AuthenticationFailureBadCredentialsEvent event) {

        String username = (String) event.getAuthentication().getPrincipal();
        processLogin(username);
    }

}
