package com.project.alumni.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String gradYear;
    private String industry;
    private String educationalDetails;
    private String professionalAchievements;
    private String profilePic;
    private CourseDto course;
    private List<Long> coursesIds;
    private Long addressId;

}
