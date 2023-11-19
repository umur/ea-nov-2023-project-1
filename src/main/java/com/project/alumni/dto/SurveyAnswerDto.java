package com.project.alumni.dto;

import com.project.alumni.dto.user.UserFullDetailsDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyAnswerDto {
    private Long id;
    private String answer;
    private int deleted;
    private SurveyDto survey;
    private UserFullDetailsDto user;
}
