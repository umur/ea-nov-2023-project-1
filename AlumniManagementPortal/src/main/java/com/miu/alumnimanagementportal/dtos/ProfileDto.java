package com.miu.alumnimanagementportal.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.miu.alumnimanagementportal.entities.Profile}
 */
@Value
public class ProfileDto implements Serializable {
    Long id;
    Long version;
    Date createdDate;
    Date lastModifiedDate;
    String phone;
    String profileImage;
    Set<WorkExperienceDto> workExperiences;
    Set<ProfessionalAchievementDto> professionalAchievements;
    Set<EducationDetailsDto> educationDetails;
}