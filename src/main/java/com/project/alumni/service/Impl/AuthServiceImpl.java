package com.project.alumni.service.Impl;

import com.project.alumni.dto.UserLoginDto;
import com.project.alumni.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Implementing our own Login logic using DAO Authentication provider for now
     * instead of Using Spring Authentication Login functionality.
     * To be extended later after attending Spring Security lecture.
     * */
    @Override
    public String login(UserLoginDto loginDto) {
        return null;
    }
}
