package com.project.alumni.service;

import com.project.alumni.dto.SurveyAnswerDto;

import java.util.List;

public interface SurveyAnswerService {
    void save(SurveyAnswerDto surveyAnswer);
    List<SurveyAnswerDto> findAll();
    SurveyAnswerDto findById(Long id);
    void update(Long id, SurveyAnswerDto surveyAnswer);
    void deleteById(Long id);
}
