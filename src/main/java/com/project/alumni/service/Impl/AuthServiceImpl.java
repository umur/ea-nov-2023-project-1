package com.project.alumni.service.Impl;

import com.project.alumni.dto.UserLoginDto;
import com.project.alumni.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Implementing our own Login logic using DAO Authentication provider for now
     * instead of Using Spring Authentication Login functionality.
     * To be extended later after attending Spring Security lecture.
     * */

    private AuthenticationManager authenticationManager;

//    public AuthServiceImpl(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @Override
    public String login(UserLoginDto loginDto) {
        return null;
    }
}
