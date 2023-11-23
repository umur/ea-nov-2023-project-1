package com.alumni.userservice.Controller;


import com.alumni.userservice.Dto.RegistrationDTO;
import com.alumni.userservice.Dto.UserDTO;
import com.alumni.userservice.Entity.User;
import com.alumni.userservice.Service.RegistrationService;
import com.alumni.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService service;
    private final RegistrationService registrationService;
    @GetMapping("")
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return service.findById(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        return service.loginUser(email, password);
    }

    @PostMapping("/register")
    public UserDTO addUser(@RequestBody RegistrationDTO registrationDTO) throws IllegalArgumentException {
        return registrationService.register(registrationDTO);

    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam String email) {
        return service.resetPassword(email);
    }

    @PostMapping("/activate/{userId}")
    public ResponseEntity<String> activateAndDeactivateUser(@PathVariable Long userId) {
        //check admin role

        //main operation
        User user = service.changeActive(userId);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user.getShowYn() == true ? "Active" : "Deactivated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
