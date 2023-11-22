package com.alumni.jobservice.Service;

import com.alumni.jobservice.Dto.JobDTO;
import com.alumni.jobservice.Entity.Job;

import java.util.List;

public interface JobService {
    void saveJob(String email, JobDTO job);
    List<Job> findAll();
    List<Job> findJobByOrganization(String organization);

    List<Job> findJobByLocationState(String state);

    List<Job> findJobByLocationCity(String city);

    List<Job> findJobsByFilter(String organization, String state, String city);
}
