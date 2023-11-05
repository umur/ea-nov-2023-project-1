package com.project.alumni.service.Impl;

import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.entity.User;
import com.project.alumni.repository.UserRepository;
import com.project.alumni.service.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;

    private final UserRepository userRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public UserMinimalDto createUser(UserMinimalDto minimalDto) {
        // Convert Dto to Entity
        User user = modelMapper.map(minimalDto, User.class);
        User newUser = userRepo.save(user);
        // Convert Entity to Dto
        return modelMapper.map(newUser, UserMinimalDto.class);
    }


} // End of UserServiceImpl class
