package com.ea.services;

import lombok.AllArgsConstructor;
import com.ea.entities.AuthRequest;
import com.ea.entities.AuthResponse;
import com.ea.entities.UserVO;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        //do validation if user exists in DB
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO registeredUser = restTemplate.postForObject("http://user-service/users", request, UserVO.class);

        String accessToken = jwtUtil.generateToken(registeredUser, "ACCESS");
        String refreshToken = jwtUtil.generateToken(registeredUser, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

}
