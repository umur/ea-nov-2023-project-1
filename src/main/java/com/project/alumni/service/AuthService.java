package com.project.alumni.service;

import com.project.alumni.dto.UserLoginDto;

public interface AuthService {
    public String login(UserLoginDto loginDto);
}
