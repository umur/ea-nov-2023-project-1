package com.project.auth.controllers;

import com.project.auth.entities.AuthRequest;
import com.project.auth.entities.AuthResponse;
import com.project.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        var data = authService.register(authRequest);
        if(data != null)
            return ResponseEntity.ok(data);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Can not saved.");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        var res = authService.login(authRequest);

        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            // Return a ResponseEntity with an error status
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Authentication failed");
        }
    }
}
