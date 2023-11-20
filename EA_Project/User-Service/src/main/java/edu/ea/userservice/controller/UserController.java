package edu.ea.userservice.controller;


import edu.ea.userservice.dto.AuthRequest;
import edu.ea.userservice.model.User;
import edu.ea.userservice.service.UserService;
import edu.ea.userservice.service.impl.JwtService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final  AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User addNewUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }
}


