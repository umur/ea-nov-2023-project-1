package com.example.alumniproject.service.impl;

import com.example.alumniproject.dto.JobDTO;
import com.example.alumniproject.entity.Job;
import com.example.alumniproject.entity.User;
import com.example.alumniproject.repository.JobRepo;
import com.example.alumniproject.repository.UserRepo;
import com.example.alumniproject.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepo repository;
    private final UserRepo userRepository;
    @Override
    public void saveJob(String email, JobDTO job) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if(foundUser.isPresent()){
           List<Job> jobs =  (foundUser.get().getProfile().getJobs() != null && foundUser.get().getProfile().getJobs().size() > 0)  ?  foundUser.get().getProfile().getJobs() : new ArrayList<>();
           Job newJob = new Job();
           newJob.setTitle(job.getTitle());
           newJob.setDescription(job.getDescription());
           newJob.setOrganization(job.getOrganization());
           jobs.add(newJob);
           repository.saveAll(jobs);
        }
    }
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
