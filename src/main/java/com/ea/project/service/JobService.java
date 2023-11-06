package com.ea.project.service;

import com.ea.project.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface JobService {
    public Job save(Job job);
    public List<Job> getAllJobs();
    public Job getJob(Long id);

    public void deleteJob(Long id);
    public void partialupdateJob(Long id,Job job);

    public void updateJob(Long id , Job newJob);

}
