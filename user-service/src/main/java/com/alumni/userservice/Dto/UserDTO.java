package com.alumni.userservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String role;

    private ProfileDTO profile;
}
