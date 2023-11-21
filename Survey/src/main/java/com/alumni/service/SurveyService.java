package com.alumni.service;

import com.alumni.dto.SurveyDto;

import java.util.List;

public interface SurveyService {

    void save(SurveyDto surveyDto);
    List<SurveyDto> findAll();
    SurveyDto findById(Long id);
    void update(Long id, SurveyDto surveyDto);
    void delete(Long id);
}
