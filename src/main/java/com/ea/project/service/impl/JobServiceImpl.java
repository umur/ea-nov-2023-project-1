package com.ea.project.service.impl;

import com.ea.project.entity.Job;
import com.ea.project.respository.JobRepo;
import com.ea.project.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepo jobRepo;

    @Override
    public Job save(Job job) {
        jobRepo.save(job);
        return job;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public Job getJob(Long id) {
        Optional<Job> Ojob=jobRepo.findById(id);
        if (Ojob.isPresent())
        {
            return Ojob.get();
        }
        throw new RuntimeException("No Job Found With Id : "+id);
    }
}
