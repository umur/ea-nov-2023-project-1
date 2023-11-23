package com.alumni.jobservice.Service.Impl;

import com.alumni.jobservice.Dto.JobDTO;
import com.alumni.jobservice.Entity.Experience;
import com.alumni.jobservice.Entity.Job;
import com.alumni.jobservice.Entity.Location;
import com.alumni.jobservice.Repo.JobRepo;
import com.alumni.jobservice.Repo.LocationRepo;
import com.alumni.jobservice.Service.JobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepo repository;
    private final LocationRepo locationRepo;

    @Override
    public void saveJob(String email, JobDTO job) {
//        Optional<User> foundUser = userRepository.findByEmail(email);
//        if(foundUser.isPresent()){
//           List<Job> jobs =  (foundUser.get().getProfile().getJobs() != null && foundUser.get().getProfile().getJobs().size() > 0)  ?  foundUser.get().getProfile().getJobs() : new ArrayList<>();
//           Job newJob = new Job();
//           newJob.setTitle(job.getTitle());
//           newJob.setDescription(job.getDescription());
//           newJob.setOrganization(job.getOrganization());
//           jobs.add(newJob);
//           repository.saveAll(jobs);
//        }
    }

    @Override
    public void saveJob(JobDTO job) {

        Job newJob = new Job();

        newJob.setTitle(job.getTitle());
        newJob.setDescription(job.getDescription());
        newJob.setOrganization(job.getOrganization());
        newJob.setPosterId(job.getPosterId());
        newJob.setAssignerId(job.getAssignerId());
        newJob.setLocation(locationRepo.save(new Location(job.getLocation())));

        repository.save(newJob);
    }

    @Override
    public void saveJob(JobDTO job, Long posterId, Long assignerId) {
        Job newJob = new Job();

        newJob.setTitle(job.getTitle());
        newJob.setDescription(job.getDescription());
        newJob.setOrganization(job.getOrganization());
        newJob.setPosterId(posterId);
        newJob.setAssignerId(assignerId);

        newJob.setLocation(locationRepo.save(new Location(job.getLocation())));
        repository.save(newJob);
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
