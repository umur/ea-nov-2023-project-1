package com.example.alumniproject.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationDTO {
    private String degree;
    private String university;
    private int graduationYear;
    private List<CourseDTO> courses;
}
