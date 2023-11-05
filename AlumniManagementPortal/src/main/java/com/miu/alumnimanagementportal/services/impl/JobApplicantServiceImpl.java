package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.JobApplicantDto;
import com.miu.alumnimanagementportal.services.JobApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobApplicantServiceImpl implements JobApplicantService {
    @Override
    public void create(JobApplicantDto jobApplicantDto) {

    }

    @Override
    public List<JobApplicantDto> findAll() {
        return null;
    }

    @Override
    public JobApplicantDto update(JobApplicantDto jobApplicantDto, Long id) {
        return null;
    }

    @Override
    public JobApplicantDto getJobApplicantById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
