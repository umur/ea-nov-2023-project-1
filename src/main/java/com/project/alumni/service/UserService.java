package com.project.alumni.service;

import com.project.alumni.dto.SearchUsersDto;
import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.entity.User;

import java.util.List;

public interface UserService {
    // Create user information with minimal detail and user can update later
    // with UserFullDetails
    public UserMinimalDto registerUser(UserMinimalDto minimalDto);

    public List<UserFullDetailsDto> findAllUsers();

    // Update user details
    public UserFullDetailsDto updateUser(UserFullDetailsDto userFullDetailsDto, Long id);

    public UserFullDetailsDto getUserById(Long id);

    List<UserFullDetailsDto> getUsersByAddress(Long addressId);

    List<SearchUsersDto> searchUsersDirectory(String query);

} // End of UserService class
