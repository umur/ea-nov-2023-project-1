package com.alumni.userservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private UserDTO user;
    private ProfileDTO profile;
}
