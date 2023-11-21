package com.alumni.JobService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.JobService.entity.Application;

@Repository
public interface ApplicationRepository extends ListCrudRepository<Application, Long> {

}
