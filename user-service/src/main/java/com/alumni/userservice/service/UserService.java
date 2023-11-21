package com.alumni.userservice.service;

import com.project.alumni.dto.user.SearchUsersDto;
import com.project.alumni.dto.user.UserFullDetailsDto;
import com.project.alumni.dto.user.UserMinimalDto;

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

    void deleteUserById(Long id);

} // End of UserService class
