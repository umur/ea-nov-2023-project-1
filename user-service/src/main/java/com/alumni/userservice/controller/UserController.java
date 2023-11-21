package com.alumni.userservice.controller;

import com.alumni.userservice.payload.SearchUsersDto;
import com.alumni.userservice.payload.UserFullDetailsDto;
import com.alumni.userservice.payload.UserMinimalDto;
import com.alumni.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<UserFullDetailsDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // create user REST api (http://localhost:8080/api/users/register)
    @PostMapping("/register")
    public ResponseEntity<UserMinimalDto> registerUser(@Valid @RequestBody UserMinimalDto minimalDto) {
        return new ResponseEntity<>(userService.registerUser(minimalDto), HttpStatus.CREATED);
    }

    // Update user details REST API (http://localhost:8080/api/users/update/{id})
    @PutMapping("/update/{id}")
    public ResponseEntity<UserFullDetailsDto> updateUserDetails(@Valid
                                                                @RequestBody UserFullDetailsDto userFullDetailsDto, @PathVariable(name = "id") Long id) {

        UserFullDetailsDto newUserDetails = userService.updateUser(userFullDetailsDto, id);

        return new ResponseEntity<>(newUserDetails, HttpStatus.OK);
    }

    // Get user details by ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<UserFullDetailsDto> getUserProfile(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Get users by addresses REST API (http://localhost:8080/api/users/address/1)
    @GetMapping("/address/{id}")
    public ResponseEntity<List<UserFullDetailsDto>> getUsersByAddress(@PathVariable(name = "id") Long addressId) {
        List<UserFullDetailsDto> userDtos = userService.getUsersByAddress(addressId);
        return ResponseEntity.ok(userDtos);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<SearchUsersDto>> searchUserDirectory(@RequestParam("query") String query){
//        return ResponseEntity.ok(userService.searchUsersDirectory(query));
//    }

    // delete category by {id} REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id){

        userService.deleteUserById(id);

        return new ResponseEntity<>("User entity deleted successfully.", HttpStatus.OK);
    }


} // End of UserController class
