package edu.miu.ea.ap.helper.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.miu.ea.ap.helper.UtilsMaster;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import edu.miu.ea.ap.model.domain.APUser;
import edu.miu.ea.ap.service.APUserService;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    APUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${spring.jackson.date-format}")
    private String DATE_FORMAT;
    @Value("${spring.jackson.time-zone}")
    private String TIME_ZONE;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        APUser currentUser = userService.getUserByUsername(user.getUsername());
        APUserResponseDTO userResponseDTO = modelMapper.map(currentUser, APUserResponseDTO.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        Map<String, Object> userHashMap = UtilsMaster.packetObject("user", mapper.convertValue(userResponseDTO, new TypeReference<Map<String, Object>>() {}));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userHashMap);
        userService.updateLastSignIn(currentUser);
        return super.enhance(accessToken, authentication);
    }

}
