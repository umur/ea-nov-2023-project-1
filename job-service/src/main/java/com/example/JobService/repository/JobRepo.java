package com.example.JobService.repository;

import com.example.JobService.entity.Job;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends ListCrudRepository<Job,Long > {

}
