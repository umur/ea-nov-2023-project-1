package com.alumni.service;

import com.alumni.dto.SurveyAnswerDto;

import java.util.List;

public interface SurveyAnswerService {
    void save(SurveyAnswerDto surveyAnswer);
    List<SurveyAnswerDto> findAll();
    SurveyAnswerDto findById(Long id);
    void update(Long id, SurveyAnswerDto surveyAnswer);
    void deleteById(Long id);
}
