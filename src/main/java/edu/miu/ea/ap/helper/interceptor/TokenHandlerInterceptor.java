package edu.miu.ea.ap.helper.interceptor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Jwt jwt = JwtHelper.decode((String) ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Map claims = objectMapper.readValue(jwt.getClaims(), Map.class);
            Map userMap = (Map) claims.get("user");
            APUserResponseDTO userResponseDTO = objectMapper.convertValue(userMap, APUserResponseDTO.class);
            ((AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).setDetails(userResponseDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

}
