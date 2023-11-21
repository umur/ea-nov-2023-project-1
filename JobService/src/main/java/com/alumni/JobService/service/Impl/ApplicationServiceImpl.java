package com.alumni.JobService.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alumni.JobService.dto.ApplicationDto;
import com.alumni.JobService.entity.Application;
import com.alumni.JobService.entity.ApplicationStatus;
import com.alumni.JobService.repository.ApplicationRepository;
import com.alumni.JobService.repository.JobRepository;
import com.alumni.JobService.service.ApplicationService;
import com.alumni.JobService.service.UserServiceClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepo;
    private final JobRepository jobRepo;
    private final UserServiceClient userRepo;
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
            var appDto = mapper.map(application, ApplicationDto.class);
            appDto.setJobId(application.getJob().getId());
            res.add(appDto);
        });
        return res;
    }

    @Override
    public ApplicationDto findById(Long id) {
        var application = applicationRepo.findById(id);

        if (application.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var appDto = mapper.map(application, ApplicationDto.class);
        appDto.setJobId(application.get().getJob().getId());

        return appDto;
    }

    @Override
    public void update(Long id, ApplicationDto updatedApplication) {
        var dbApplication = applicationRepo.findById(id);
        if (dbApplication.isPresent()) {
            dbApplication.get().setStatus(updatedApplication.getStatus());

            var user = userRepo.getUserById(updatedApplication.getUserId());
            dbApplication.get().setUserId(updatedApplication.getUserId());

            var job = jobRepo.findById(updatedApplication.getJobId()).orElse(null);

            if (updatedApplication.getStatus().equals(ApplicationStatus.Accepted)) {
                job.setUserId(user.getId());
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
