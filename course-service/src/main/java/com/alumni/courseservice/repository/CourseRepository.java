package com.alumni.courseservice.repository;

import com.alumni.courseservice.entity.Course;
import org.springframework.data.repository.ListCrudRepository;

public interface CourseRepository extends ListCrudRepository<Course, Long> {
}
