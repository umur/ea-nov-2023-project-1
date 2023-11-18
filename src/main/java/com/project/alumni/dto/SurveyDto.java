package com.project.alumni.dto;

import com.project.alumni.dto.user.UserFullDetailsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SurveyDto {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private UserFullDetailsDto user;
}
