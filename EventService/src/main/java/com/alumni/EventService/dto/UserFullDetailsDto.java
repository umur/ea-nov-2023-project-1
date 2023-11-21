package com.alumni.EventService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFullDetailsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gradYear;
    private String industry;
    private String educationalDetails;
    private String professionalAchievements;
    private String profilePic;
    private Long addressId;

}
