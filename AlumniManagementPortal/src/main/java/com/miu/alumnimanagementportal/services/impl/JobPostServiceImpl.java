package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.services.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobPostServiceImpl implements JobPostService {
    @Override
    public void create(JobPostDto jobPostDto) {

    }

    @Override
    public List<JobPostDto> findAll() {
        return null;
    }

    @Override
    public JobPostDto update(JobPostDto jobPostDto, Long id) {
        return null;
    }

    @Override
    public JobPostDto getAddress(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
