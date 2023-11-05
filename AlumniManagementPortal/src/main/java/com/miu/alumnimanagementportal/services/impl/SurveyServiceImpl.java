package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.SurveyDto;
import com.miu.alumnimanagementportal.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {
    @Override
    public void create(EducationDetailsDto educationDetailsDto) {

    }

    @Override
    public List<SurveyDto> findAll() {
        return null;
    }

    @Override
    public SurveyDto update(SurveyDto surveyDto, Long id) {
        return null;
    }

    @Override
    public SurveyDto getSurveyById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
