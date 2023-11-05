package com.project.alumni.service.Impl;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.entity.User;
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
    public UserMinimalDto createUser(UserMinimalDto minimalDto) {
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


} // End of UserServiceImpl class
