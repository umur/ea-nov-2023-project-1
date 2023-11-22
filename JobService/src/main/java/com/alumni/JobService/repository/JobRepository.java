package com.alumni.JobService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.JobService.entity.Job;

@Repository
public interface JobRepository extends ListCrudRepository<Job, Long> {

}
