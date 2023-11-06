package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.UserDto;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.UserRepository;
import com.miu.alumnimanagementportal.services.UserService;
import com.miu.alumnimanagementportal.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
