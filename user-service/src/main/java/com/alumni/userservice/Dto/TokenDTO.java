package com.alumni.userservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String firstname;
    private String email;
    private String token;
}
