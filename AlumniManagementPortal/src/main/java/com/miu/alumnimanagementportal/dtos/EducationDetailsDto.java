package com.miu.alumnimanagementportal.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.miu.alumnimanagementportal.entities.EducationDetails}
 */
@Value
public class EducationDetailsDto implements Serializable {
    Long id;
    Long version;
    Date createdDate;
    Date lastModifiedDate;
    String institutionName;
    String degree;
    @Size(min = 4, max = 4)
    String passingYear;
    String cgpa;
    String course;
}