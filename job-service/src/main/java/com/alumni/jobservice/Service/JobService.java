package com.alumni.jobservice.Service;

import com.alumni.jobservice.Dto.JobDTO;
import com.alumni.jobservice.Entity.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    void saveJob(String email, JobDTO job);
    void saveJob(JobDTO job);
    void saveJob(JobDTO job, Long posterId, Long assignerId);
    List<Job> findAll();

    void activatePosterJobs(Long userId, boolean isShow);

    List<Job> findJobByOrganization(String organization);

    List<Job> findJobByLocationState(String state);

    List<Job> findJobByLocationCity(String city);

    List<Job> findJobsByFilter(String organization, String state, String city);

}
