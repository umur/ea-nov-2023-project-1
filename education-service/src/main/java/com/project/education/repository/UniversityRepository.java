package com.project.education.repository;

import com.project.education.model.University;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends ListCrudRepository<University, Long> {
}
