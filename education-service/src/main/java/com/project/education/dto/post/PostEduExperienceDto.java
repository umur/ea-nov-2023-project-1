package com.project.education.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostEduExperienceDto {
    private Long universityId;
    private String degree;
    private LocalDate startTime;
    private LocalDate endTime;
    private List<Long> courseIdList;
}
