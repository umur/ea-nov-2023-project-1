package com.miu.alumnimanagementportal.services;


import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.RoleDto;
import com.miu.alumnimanagementportal.dtos.SurveyDto;

import java.util.List;

public interface SurveyService { void create(EducationDetailsDto educationDetailsDto);
    void create(RoleDto roleDto);


    List<SurveyDto> findAll();

    SurveyDto update(SurveyDto surveyDto, Long id);

    SurveyDto getSurveyById(Long id);

    void delete(Long id);
}
