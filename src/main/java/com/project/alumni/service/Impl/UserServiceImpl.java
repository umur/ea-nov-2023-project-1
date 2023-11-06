package com.project.alumni.service.Impl;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.entity.User;
import com.project.alumni.exceptions.ResourceNotFoundException;
import com.project.alumni.repository.UserRepository;
import com.project.alumni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;

    private final UserRepository userRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
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
        user.setId(id);
        user.setGradYear(userFullDetailsDto.getGradYear());
        user.setEducationalDetails(userFullDetailsDto.getEducationalDetails());
        user.setIndustry(userFullDetailsDto.getIndustry());
        user.setProfessionalAchievements(userFullDetailsDto.getProfessionalAchievements());
        user.setProfilePic(userFullDetailsDto.getProfilePic());

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
    public List<UserFullDetailsDto> searchUsers(String query) {
        List<User> users = userRepo.searchUsers(query);
        return users.stream().map((u) -> modelMapper.map(u, UserFullDetailsDto.class))
                .collect(Collectors.toList());
    }



} // End of UserServiceImpl class
