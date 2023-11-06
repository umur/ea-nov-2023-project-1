package com.project.alumni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserFullDetailsDto {
    private Long id;
    // First name should not be null or empty
    // First name should have at least 2 Characters.
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String graduationYear;
    private String industry;
    private String educationalDetails;
    private String professionalAchievements;
    private String profilePic;

}
