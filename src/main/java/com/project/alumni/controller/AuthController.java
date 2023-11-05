package com.project.alumni.controller;

import com.project.alumni.dto.UserLoginDto;
import com.project.alumni.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(UserLoginDto loginDto){
        String response = authService.login(loginDto);
         return ResponseEntity.ok(response);
    }
}
