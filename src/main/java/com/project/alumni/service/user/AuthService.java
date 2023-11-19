package com.project.alumni.service.user;

import com.project.alumni.dto.user.LoginDto;
import com.project.alumni.dto.user.UserMinimalDto;

public interface AuthService {
    public String login(LoginDto loginDto);

    public String register(UserMinimalDto registerDto);
}
