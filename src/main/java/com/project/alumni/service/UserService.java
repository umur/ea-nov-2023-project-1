package com.project.alumni.service;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.UserMinimalDto;

import java.util.List;

public interface UserService {
    // Create user information with minimal detail and user can update later
    // with UserFullDetails
    public UserMinimalDto registerUser(UserMinimalDto minimalDto);

    // Find all Users
    public List<UserFullDetailsDto> findAllUsers();

}
