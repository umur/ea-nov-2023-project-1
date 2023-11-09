package com.example.alumniproject.service.impl;

import com.example.alumniproject.entity.Job;
import com.example.alumniproject.repository.JobRepo;
import com.example.alumniproject.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepo repository;

    @Override
    public List<Job> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Job> findJobByOrganization(String organization) {
        return repository.getJobsByOrganization(organization);
    }

    @Override
    public List<Job> findJobByLocationState(String state) {
        return repository.getJobsByLocationState(state);
    }

    @Override
    public List<Job> findJobByLocationCity(String city) {
        return repository.getJobsByLocationCity(city);
    }

    @Override
    public List<Job> findJobsByFilter(String organization, String state, String city) {
        return repository.getJobsByLocationCityOrLocationStateOrOrganization(city, state, organization);
    }
}
