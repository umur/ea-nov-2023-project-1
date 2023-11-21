package com.alumni.userservice.service;

import com.alumni.userservice.payload.LoginDto;
import com.alumni.userservice.payload.UserMinimalDto;

public interface AuthService {
    public String login(LoginDto loginDto);

    public String register(UserMinimalDto registerDto);
}
