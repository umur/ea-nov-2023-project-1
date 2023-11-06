package com.miu.alumnimanagementportal.services;

import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.UserActivationDto;
import com.miu.alumnimanagementportal.dtos.UserDto;
import com.miu.alumnimanagementportal.dtos.UserLoginInfoDto;

import java.util.List;

public interface UserService {

    UserDto register(UserDto userDto);

    void create(UserDto userDto);

    List<UserDto> findAll();

    UserDto update(UserDto userDto, Long id);

    UserDto getUserById(Long id);

    void delete(Long id);

    UserActivationDto update(UserActivationDto userActivationDto, Long id);

    void login(UserLoginInfoDto userLoginInfoDto);
}
