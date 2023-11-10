package com.example.alumniproject.service;

import com.example.alumniproject.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<?> resetPassword(String email);
    ResponseEntity<?> loginUser(String email, String password);
    boolean isUserLockedOut(User user);
    void lockUserAccount(User user);
    void save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User changeActive(Long userId);
}
