package com.project.alumni.repository.Job;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.alumni.entity.Job.Job;

@Repository
public interface JobRepository extends ListCrudRepository<Job, Long> {

}
