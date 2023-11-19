package com.project.alumni.service.user.impl;

import com.project.alumni.dto.user.LoginDto;
import com.project.alumni.dto.user.UserMinimalDto;
import com.project.alumni.entity.user.Role;
import com.project.alumni.entity.user.User;
import com.project.alumni.exceptions.GlobalAPIException;
import com.project.alumni.repository.user.RoleRepository;
import com.project.alumni.repository.user.UserRepository;
import com.project.alumni.security.JwtTokenProvider;
import com.project.alumni.service.user.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Implemented our own Login logic using DAO Authentication provider and JWT Authentication Token
     * */

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;



    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepo, RoleRepository roleRepo,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
    @Override
    public String register(UserMinimalDto registerDto) {
        // Checking for existing username already exists in the database
        if(userRepo.existsByUsername(registerDto.getUsername())){
            throw new GlobalAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        // Checking for email already exists in the database
        if(userRepo.existsByEmail(registerDto.getEmail())){
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
}
