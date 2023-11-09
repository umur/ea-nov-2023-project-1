package com.example.alumniproject.service.impl;

import com.example.alumniproject.dto.TokenDTO;
import com.example.alumniproject.entity.Role;
import com.example.alumniproject.entity.User;
import com.example.alumniproject.repository.UserRepo;
import com.example.alumniproject.service.UserService;
import com.example.alumniproject.util.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repository;
    private Jwt jwtUtil = new Jwt();

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Override
    public User changeActive(Long userId) {
        Optional<User> userOptional = this.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setShowYn(!user.getShowYn());
            return repository.save(user);
        }
        else{
            return null;
        }
    }

    @Override
    public boolean isUserLockedOut(User user){
        LocalDateTime  lastFailedLogin = user.getLastFailedLoginTimestamp();
        if(lastFailedLogin != null)
        {
            LocalDateTime currentTime = LocalDateTime.now();
            long minDifference = Duration.between(lastFailedLogin, currentTime).toMinutes();

            if(minDifference < 15 && (user.getAccountLocked() != null &&  user.getAccountLocked())){
                return true;
            }
            else {
                user.setAccountLocked(false);
                user.setLastFailedLoginTimestamp(null);
                repository.save(user);
                return false;
            }
        }
        return false;
    }
    @Override
    public void lockUserAccount(User user){
        user.setAccountLocked(true);
        user.setLastFailedLoginTimestamp(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    public ResponseEntity<?> loginUser(String email, String password){
        Optional<User> existingUser = this.findByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (this.isUserLockedOut(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account locked. Try again later 15 minutes later.");
            }
            if (user.getPassword().equals(password)) {
                String token = jwtUtil.generateToken(email, existingUser.get().getRole().toString());
                TokenDTO result = new TokenDTO();
                result.setFirstname(user.getFirstName());
                result.setEmail(email);
                result.setToken(token);
                return ResponseEntity.ok(result);
            } else {
                user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
                user.setLastFailedLoginTimestamp(LocalDateTime.now());
                this.save(user);
                if (user.getFailedLoginAttempts() >= 5) {
                    this.lockUserAccount(user);
                    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Account locked. Cause of too many failed attempts.");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

}
