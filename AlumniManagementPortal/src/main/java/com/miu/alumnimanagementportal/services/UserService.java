package com.miu.alumnimanagementportal.services;

import com.miu.alumnimanagementportal.dtos.*;

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
    ResetPasswordDto resetPassword(ResetPasswordDto resetPassworddto);


}
