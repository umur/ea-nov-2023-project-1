package com.alumni.userservice.service.impl;

import com.alumni.userservice.entity.Address;
import com.alumni.userservice.entity.User;
import com.alumni.userservice.exceptions.ResourceNotFoundException;
import com.alumni.userservice.payload.UserFullDetailsDto;
import com.alumni.userservice.payload.UserMinimalDto;
import com.alumni.userservice.repository.AddressRepository;
import com.alumni.userservice.repository.UserRepository;
import com.alumni.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepo,
                           AddressRepository addressRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public UserMinimalDto registerUser(UserMinimalDto minimalDto) {
        // Convert Dto to Entity
        User user = modelMapper.map(minimalDto, User.class);
        User newUser = userRepo.save(user);
        // Convert Entity to Dto
        return modelMapper.map(newUser, UserMinimalDto.class);
    }

    @Override
    public List<UserFullDetailsDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((u) -> modelMapper.map(u, UserFullDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserFullDetailsDto updateUser(UserFullDetailsDto userFullDetailsDto, Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));

        // Get Address by Id from the database.
        Address address = addressRepo.findById(userFullDetailsDto.getAddressId()).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", userFullDetailsDto.getAddressId()));

        user.setId(id);
        user.setGradYear(userFullDetailsDto.getGradYear());
        user.setEducationalDetails(userFullDetailsDto.getEducationalDetails());
        user.setIndustry(userFullDetailsDto.getIndustry());
        user.setProfessionalAchievements(userFullDetailsDto.getProfessionalAchievements());
        user.setProfilePic(userFullDetailsDto.getProfilePic());

        // Set address before saving user to the database.
        user.setAddress(address);

        User savedUser = userRepo.save(user);

        return modelMapper.map(savedUser, UserFullDetailsDto.class);
    }

    @Override
    public UserFullDetailsDto getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user, UserFullDetailsDto.class);
    }

    @Override
    public List<UserFullDetailsDto> getUsersByAddress(Long addressId) {
        // Get address by Id from the database.
        Address address = addressRepo.findById(addressId).orElseThrow(() ->
                new ResourceNotFoundException("Address", "id", addressId));

        List<User> users = userRepo.findByAddressId(addressId);

        return users.stream().map(u -> modelMapper.map(u, UserFullDetailsDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<SearchUsersDto> searchUsersDirectory(String query) {
//        List<User> users = userRepo.searchUsersDirectory(query);
//        return users.stream().map(u -> modelMapper.map(u, SearchUsersDto.class ))
//                .collect(Collectors.toList());
//    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
    }

} // End of UserServiceImpl class
