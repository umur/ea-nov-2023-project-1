package com.miu.alumnimanagementportal.services;

import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.UserDto;

import java.util.List;

public interface UserService {

    void create(UserDto userDto);

    List<UserDto> findAll();

    UserDto update(UserDto userDto, Long id);

    UserDto getUserById(Long id);

    void delete(Long id);
}
