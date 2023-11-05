package com.project.alumni.controller;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combination of @Controller and @ResponseBody annotations
@RequestMapping("/api/users") // Base URL for product REST API
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

      /*
    Implement CRUD operations for the domains.*/
      // Get all users REST API
      @GetMapping
      public ResponseEntity<List<UserFullDetailsDto>> getAllUsers(){
          return ResponseEntity.ok(userService.findAllUsers());
      }

    // create user REST api (http://localhost:8080/api/users/create)
    @PostMapping("/create")
    public ResponseEntity<UserMinimalDto> createUser(@RequestBody UserMinimalDto minimalDto){
        return new ResponseEntity<>(userService.createUser(minimalDto), HttpStatus.CREATED);
    }


} // End of UserController class
