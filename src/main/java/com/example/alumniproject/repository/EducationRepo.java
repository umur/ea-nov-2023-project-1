package com.example.alumniproject.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.alumniproject.entity.Education;

@Repository
public interface EducationRepo extends ListCrudRepository<Education, Long> {

}
