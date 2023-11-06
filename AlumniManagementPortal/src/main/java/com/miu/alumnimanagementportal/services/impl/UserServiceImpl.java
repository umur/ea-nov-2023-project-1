package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.UserActivationDto;
import com.miu.alumnimanagementportal.dtos.UserDto;
import com.miu.alumnimanagementportal.dtos.UserLoginInfoDto;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.UserRepository;
import com.miu.alumnimanagementportal.services.UserService;
import com.miu.alumnimanagementportal.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final Converter converter;
    @Override
    public UserDto register(UserDto userDto) {
        Optional.ofNullable(userDto.getId()).ifPresent(id -> {
            if (repository.existsById(id)) {
                throw new DataAlreadyExistException("User with id " + id + " already exists");
            }
        });
        return converter.convert(repository.save(converter.convert(userDto, User.class)), UserDto.class);
    }

    @Override
    public void create(UserDto userDto) {

    }

    @Override
    public List<UserDto> findAll() {
        return converter.convertList(repository.findAll(), UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        return Optional.ofNullable(userDto.getId()).map(entityId -> {
            if (!repository.existsById(entityId)) {
                throw new ResourceNotFoundException("User with id " + entityId + " not found");
            }
            return converter.convert(
                    repository.save(
                            converter.convert(userDto, User.class)
                    ), UserDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }



    @Override
    public UserDto getUserById(Long id) {
        return Optional.ofNullable(id)
                .map(repository::findById)
                .map(user -> converter.convert(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        Optional.ofNullable(id).ifPresent(repository::deleteById);
    }



    @Override
    public UserActivationDto update(UserActivationDto userActivationDto, Long id) {
        return Optional.ofNullable(userActivationDto.getId()).map(entityId -> {
            if (!repository.existsById(entityId)) {
                throw new ResourceNotFoundException("User with id " + entityId + " not found");
            }
            return converter.convert(
                    repository.save(
                            converter.convert(userActivationDto, User.class)
                    ), UserActivationDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("User  not found"));
    }

    private static final int MAX_LOGIN_ATTEMPTS = 5;
    // 15 minutes in milliseconds

    @Override
    public void login(UserLoginInfoDto userLoginInfoDto) {
        Optional.ofNullable(userLoginInfoDto.getEmail())
                .map(repository::findByEmail)
                .ifPresent(user -> {
                    if (user.is_locked()) {
                        throw new ResourceNotFoundException("User is locked");
                    }
                    if (user.getLoginCount() >= MAX_LOGIN_ATTEMPTS) {
                        throw new ResourceNotFoundException("User reached max login attempts");
                    }
                    LocalDateTime lastLockedDateTime = LocalDateTime.now();
                    Duration duration = Duration.between(lastLockedDateTime, LocalDateTime.now());
                    if (duration.toMinutes() > 15) {
                        throw new ResourceNotFoundException("User is locked");
                    }
                    if (!user.getPassword().equals(userLoginInfoDto.getPassword())) {
                        user.setLoginCount(user.getLoginCount() + 1);
                        throw new ResourceNotFoundException("Incorrect password");
                    }
                    user.setLoginCount(0);
                });
    }


}
