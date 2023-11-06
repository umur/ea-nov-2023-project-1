package com.miu.alumnimanagementportal.services;


import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.dtos.JobPostDto;

import java.util.List;

public interface JobPostService {
    void create(JobPostDto jobPostDto);

    List<JobPostDto> findAll();

    JobPostDto update(JobPostDto jobPostDto, Long id);

    JobPostDto getjobPostById(Long id);

    void delete(Long id);
}
