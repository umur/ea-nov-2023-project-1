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
    @NotEmpty
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    private String graduationYear;
    private String industry;
    private String educationalDetails;
    private String professionalAchievements;
    private String profilePic;

}
