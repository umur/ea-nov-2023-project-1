package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.dtos.JobPostsByFilterDto;
import com.miu.alumnimanagementportal.entities.JobPost;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.JobPostRepository;
import com.miu.alumnimanagementportal.services.JobPostService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobPostServiceImpl implements JobPostService {
    private final JobPostRepository jobPostRepository;
    private final Converter converter;

    private final EntityManager entityManager;

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

    @Override
    public List<JobPostDto> getJobPostsByFilter(JobPostsByFilterDto jobPostsByFilterDto) {
        Map<String, Object> filterParams = new HashMap<>();
        StringBuilder query = new StringBuilder("SELECT jp FROM JobPost jp WHERE 1 = 1");

        if (jobPostsByFilterDto.getLocation() != null) {
            query.append(" AND jp.location = :location");
            filterParams.put("location", jobPostsByFilterDto.getLocation());
        }
        if (jobPostsByFilterDto.getCompanyName() != null) {
            query.append(" AND jp.companyName = :companyName");
            filterParams.put("companyName", jobPostsByFilterDto.getCompanyName());
        }
        if (jobPostsByFilterDto.getCity() != null) {
            query.append(" AND jp.city = :city");
            filterParams.put("city", jobPostsByFilterDto.getCity());
        }
        if (jobPostsByFilterDto.getState() != null) {
            query.append(" AND jp.state = :state");
            filterParams.put("state", jobPostsByFilterDto.getState());
        }

        TypedQuery<JobPost> typedQuery = entityManager.createQuery(query.toString(), JobPost.class);
        for (Map.Entry<String, Object> entry : filterParams.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }

        List<JobPost> filteredJobPosts = typedQuery.getResultList();

        List<JobPostDto> filteredJobPostDtos = filteredJobPosts.stream()
                .map(element -> converter.convert(element, JobPostDto.class))
                .collect(Collectors.toList());

        return filteredJobPostDtos;
    }




}
