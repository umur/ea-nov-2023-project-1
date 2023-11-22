package com.alumni.userservice.service.impl;

import com.alumni.userservice.entity.Address;
import com.alumni.userservice.entity.AuditAction;
import com.alumni.userservice.entity.User;
import com.alumni.userservice.exceptions.ResourceNotFoundException;
import com.alumni.userservice.payload.*;
import com.alumni.userservice.repository.AddressRepository;
import com.alumni.userservice.repository.UserRepository;
import com.alumni.userservice.security.CustomUserDetails;
import com.alumni.userservice.service.AuditLogService;
import com.alumni.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final WebClient webClient;

    private final AuditLogService auditLogService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepo,
                           AddressRepository addressRepo, WebClient webClient,
                           AuditLogService auditLogService) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.webClient = webClient;
        this.auditLogService = auditLogService;
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
        List<UserFullDetailsDto> fullDetailsDtoList = users.stream().map((u) -> modelMapper.map(u, UserFullDetailsDto.class))
                .toList();
        String username = getLoggedInUserUsername();
        auditLogService.save(username, AuditAction.FETCH_USERS);
        return fullDetailsDtoList;
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

    @Override
    public List<SearchUsersDto> searchUsersDirectory(String query) {
        List<User> users = userRepo.searchUsersDirectory(query);
        return users.stream().map(u -> modelMapper.map(u, SearchUsersDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
    }

    @Override
    public APIResponseDto getUserCourse(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userId));

        CourseDto courseDto = webClient.get()
                .uri("http://localhost:8808/api/courses/code/" + user.getCourseCode())
                .retrieve()
                .bodyToMono(CourseDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setUser(modelMapper.map(user, UserFullDetailsDto.class));
        apiResponseDto.setCourse(courseDto);

        return apiResponseDto;
    }


    // findAllByIdIn
    @Override
    public List<UserFullDetailsDto> findAllByIdIn(List<Long> ids) {
        List<User> users = userRepo.findAllByIdIn(ids);
        return users.stream().map((u) -> modelMapper.map(u, UserFullDetailsDto.class))
                .collect(Collectors.toList());
    }

    private String getLoggedInUserUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUsername();
    }
} // End of UserServiceImpl class
