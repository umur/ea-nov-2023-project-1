package com.alumni.userservice.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EducationDTO {
    private String degree;
    private String university;
    private int graduationYear;
    private List<CourseDTO> courses;
}
