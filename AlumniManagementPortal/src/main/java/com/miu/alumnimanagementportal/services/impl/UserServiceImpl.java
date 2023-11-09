package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.*;
import com.miu.alumnimanagementportal.exceptions.BadRequestException;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.UserRepository;
import com.miu.alumnimanagementportal.services.UserService;
import com.miu.alumnimanagementportal.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

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
    public void login(SigninDto signinDto) {
        Optional.ofNullable(signinDto.getEmail())
                .map(repository::findByEmail)
                .ifPresent(user -> {
                    try {
                        if (user.is_locked()) {
                            throw new BadRequestException("User is locked");
                        }
                        if (user.getLoginCount() >= MAX_LOGIN_ATTEMPTS) {
                            throw new BadRequestException("User reached max login attempts");
                        }
                        LocalDateTime lastLockedDateTime = LocalDateTime.now();
                        Duration duration = Duration.between(lastLockedDateTime, LocalDateTime.now());
                        if (duration.toMinutes() > 15) {
                            throw new BadRequestException("User is locked");
                        }
                        String encodedPassword = passwordEncoder.encode(signinDto.getPassword());
                        if (!user.getPassword().equals(encodedPassword)) {
                            user.setLoginCount(user.getLoginCount() + 1);
                            throw new BadRequestException("Incorrect password");
                        }
                        user.setLoginCount(0);
                    } catch (BadRequestException e) {
                        throw e;
                    } finally {
                        repository.save(user);
                    }
                });
    }

    @Override
    public ResetPasswordDto resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional.ofNullable(resetPasswordDto.getEmail())
                .map(repository::findByEmail)
                .ifPresent(user -> {
                    user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
                });
        return resetPasswordDto;
    }

    @Override
    public void signup(SignupDto signupDto) {
        Optional.ofNullable(repository.findByEmail(signupDto.getEmail())).map(signup -> {
            User user = converter.convert(signup, User.class);
            String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
            user.setPassword(encodedPassword);
            return repository.save(user);
        }).orElseThrow(() -> new DataAlreadyExistException("User with email " + signupDto.getEmail() + " already exists"));
    }
}
