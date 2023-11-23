package com.project.education.repository;

import com.project.education.model.Course;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ListCrudRepository<Course, Long> {

}
