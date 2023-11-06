package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.UserDto;
import com.miu.alumnimanagementportal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto register(UserDto userDto) {
        return null;
    }

    @Override
    public void create(UserDto userDto) {

    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
