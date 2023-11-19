package com.alumni.EventService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMinimalDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
