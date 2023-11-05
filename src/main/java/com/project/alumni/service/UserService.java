package com.project.alumni.service;

import com.project.alumni.dto.UserMinimalDto;

public interface UserService {
    // Create user information with minimal detail and user can update later
    // with UserFullDetails
    public UserMinimalDto createUser(UserMinimalDto minimalDto);
}
