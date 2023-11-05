package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.services.EducationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EducationDetailsServiceImpl implements EducationDetailsService {
    @Override
    public void create(EducationDetailsDto educationDetailsDto) {

    }

    @Override
    public List<EducationDetailsDto> findAll() {
        return null;
    }

    @Override
    public EducationDetailsDto update(EducationDetailsDto educationDetailsDto, Long id) {
        return null;
    }

    @Override
    public EducationDetailsDto getEducationDetailsById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
