package com.project.alumni.repository.Job;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.alumni.entity.Job.Application;

@Repository
public interface ApplicationRepository extends ListCrudRepository<Application, Long> {

}
