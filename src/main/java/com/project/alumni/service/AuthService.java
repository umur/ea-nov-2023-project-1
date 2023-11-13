package com.project.alumni.service;

import com.project.alumni.dto.LoginDto;
import com.project.alumni.dto.UserMinimalDto;

public interface AuthService {
    public String login(LoginDto loginDto);

    public String register(UserMinimalDto registerDto);
}
