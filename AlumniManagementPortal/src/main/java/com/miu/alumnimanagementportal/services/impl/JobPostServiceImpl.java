package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.entities.JobApplicant;
import com.miu.alumnimanagementportal.entities.JobPost;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.JobApplicantRepository;
import com.miu.alumnimanagementportal.repositories.JobPostRepository;
import com.miu.alumnimanagementportal.services.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobPostServiceImpl implements JobPostService {
    private final JobPostRepository jobPostRepository;
    private final Converter converter;

    @Override
    public void create(JobPostDto jobPostDto) {
       Optional.ofNullable(jobPostDto.getId()).ifPresent(id -> {
           if (jobPostRepository.existsById(id)) {
               throw new DataAlreadyExistException("JobPost with id " + id + " already exists");
           }
       });
       jobPostRepository.save(converter.convert(jobPostDto, JobPost.class));
    }


    @Override
    public List<JobPostDto> findAll() {
        return jobPostRepository.findAll()
                .stream()
                .map(element -> converter.convert(element, JobPostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobPostDto update(JobPostDto jobPostDto, Long id) {
        return Optional.ofNullable(jobPostDto.getId()).map(entityId -> {
            if (!jobPostRepository.existsById(entityId)) {
                throw new ResourceNotFoundException("JobPost with id " + entityId + " not found");
            }
            return converter.convert(jobPostRepository.save(converter.convert(jobPostDto, JobPost.class)), JobPostDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("JobPost with id " + id + " not found"));
    }

    @Override
    public JobPostDto getjobPostById(Long id) {
        return Optional.ofNullable(id)
                .map(jobPostRepository::findById)
                .map(element -> converter.convert(element, JobPostDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("JobPost with id " + id + " not found"));
    }


    @Override
    public void delete(Long id) {
        if (!jobPostRepository.existsById(id)) {
            throw new ResourceNotFoundException("JobPost with id " + id + " not found");
        }
        jobPostRepository.deleteById(id);
    }
}
