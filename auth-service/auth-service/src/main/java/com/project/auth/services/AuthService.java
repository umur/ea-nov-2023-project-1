package com.project.auth.services;

import com.project.auth.entities.AuthRequest;
import com.project.auth.entities.AuthResponse;
import com.project.auth.entities.value_objects.UserVO;
import com.project.auth.services.feign.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final com.project.auth.services.JwtUtil jwt;
    private final UserServiceClient userServiceClient;
    private List<String> failAttempts = new ArrayList<>(); //userid
    private Map<String,Long> blackList = new HashMap<>();

    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        UserVO userVO = userServiceClient.saveUser(authRequest);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
    public AuthResponse login(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));
        var mail = authRequest.getMail();
        if(mail == null){
            return null;
        }
        if(blackList.containsKey(mail)){
            if(blackList.get(mail) < System.currentTimeMillis()){
                blackList.remove(mail);
                for (int i = 0; i < failAttempts.size(); i++) {
                    if(failAttempts.get(i) == mail){
                        failAttempts.remove(mail);
                    }
                }
            }
            else{
                return null;
            }
        }
        if(failAttempts.stream().filter(x -> x == mail).count() == 5){
            blackList.put(mail, System.currentTimeMillis() + (15 * 60 * 1000));
            return null;
        }
        UserVO userVo = userServiceClient.getUserByMail(authRequest.getMail());

        if(userVo == null){
            return null;
        }
        if(!userVo.getPassword().equals(authRequest.getPassword())){
            failAttempts.add(authRequest.getMail());
        }

        String accessToken = jwt.generate(userVo, "ACCESS");
        String refreshToken = jwt.generate(userVo, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

}
