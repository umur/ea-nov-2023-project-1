package com.alumni.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyAnswerDto {
    private Long id;
    private String answer;
    private int deleted;
    private SurveyDto survey;
    private ExternalUserDto user;
}
