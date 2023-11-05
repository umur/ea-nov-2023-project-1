package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.WorkExperienceDto;
import com.miu.alumnimanagementportal.services.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    @Override
    public void create(WorkExperienceDto workExperienceDto) {

    }

    @Override
    public List<WorkExperienceDto> findAll() {
        return null;
    }

    @Override
    public WorkExperienceDto update(WorkExperienceDto workExperienceDto, Long id) {
        return null;
    }

    @Override
    public WorkExperienceDto getAddress(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
