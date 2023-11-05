package com.miu.alumnimanagementportal.services;


import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.WorkExperienceDto;

import java.util.List;

public interface WorkExperienceService {
    void create(WorkExperienceDto workExperienceDto);

    List<WorkExperienceDto> findAll();

    WorkExperienceDto update(WorkExperienceDto workExperienceDto, Long id);

    WorkExperienceDto getAddress(Long id);

    void delete(Long id);
}
