package com.project.alumni.service.Job.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.alumni.dto.Job.ApplicationDto;
import com.project.alumni.entity.Job.Application;
import com.project.alumni.entity.Job.ApplicationStatus;
import com.project.alumni.repository.UserRepository;
import com.project.alumni.repository.Job.ApplicationRepository;
import com.project.alumni.repository.Job.JobRepository;
import com.project.alumni.service.Job.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepo;
    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    @Override
    public void save(ApplicationDto applicationDto) {
        applicationRepo.save(mapper.map(applicationDto, Application.class));
    }

    @Override
    public List<ApplicationDto> findAll() {
        List<Application> applications = applicationRepo.findAll();
        var res = new ArrayList<ApplicationDto>();
        applications.forEach(application -> {
            res.add(mapper.map(application, ApplicationDto.class));
        });
        return res;
    }

    @Override
    public ApplicationDto findById(Long id) {
        var res = applicationRepo.findById(id);
        return mapper.map(res, ApplicationDto.class);
    }

    @Override
    public void update(Long id, ApplicationDto updatedApplication) {
        var dbApplication = applicationRepo.findById(id);
        if (dbApplication.isPresent()) {
            dbApplication.get().setStatus(updatedApplication.getStatus());

            var user = userRepo.findById(updatedApplication.getUserId()).orElse(null);
            dbApplication.get().setUser(user);

            var job = jobRepo.findById(updatedApplication.getJobId()).orElse(null);
            if (updatedApplication.getStatus().equals(ApplicationStatus.Accepted)) {
                job.setUser(user);
            }
            dbApplication.get().setJob(job);

            applicationRepo.save(dbApplication.get());
        }
    }

    @Override
    public void delete(Long id) {
        applicationRepo.deleteById(id);
    }
}
