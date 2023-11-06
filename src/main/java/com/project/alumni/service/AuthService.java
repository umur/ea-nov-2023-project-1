package com.project.alumni.service;

import com.project.alumni.dto.UserLoginDto;
import com.project.alumni.dto.UserMinimalDto;

public interface AuthService {
    public String login(UserLoginDto loginDto);

    String register(UserMinimalDto registerDto);
}
