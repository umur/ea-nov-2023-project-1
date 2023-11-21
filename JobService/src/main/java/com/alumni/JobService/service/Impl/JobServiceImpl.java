package com.alumni.JobService.service.Impl;

import com.alumni.JobService.dto.JobDto;
import com.alumni.JobService.entity.Job;
import com.alumni.JobService.repository.JobRepository;
import com.alumni.JobService.service.JobService;
import com.alumni.JobService.service.UserServiceClient;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepo;
    private final UserServiceClient userRepo;
    private final ModelMapper mapper;

    @Override
    public void save(JobDto jobDto) {
        jobRepo.save(mapper.map(jobDto, Job.class));
    }

    @Override
    public List<JobDto> findAll() {
        List<Job> jobs = jobRepo.findAll();
        var res = new ArrayList<JobDto>();
        jobs.forEach(job -> {
            res.add(mapper.map(job, JobDto.class));
        });
        return res;
    }

    @Override
    public JobDto findById(Long id) {
        var res = jobRepo.findById(id);
        return mapper.map(res, JobDto.class);
    }

    @Override
    public void update(Long id, JobDto updatedJob) {
        var dbJob = jobRepo.findById(id);
        if (dbJob.isPresent()) {
            dbJob.get().setDescription(updatedJob.getDescription());
            dbJob.get().setName(updatedJob.getName());
            dbJob.get().setRequirements(updatedJob.getRequirements());
            var user = userRepo.getUserById(updatedJob.getUserId());
            dbJob.get().setUserId(user.getId());
            jobRepo.save(dbJob.get());
        }
    }

    @Override
    public void delete(Long id) {
        jobRepo.deleteById(id);
    }
}
