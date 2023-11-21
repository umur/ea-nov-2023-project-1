package com.dunky.enrollmentservice.repository;

import com.dunky.enrollmentservice.entity.Enrollment;
import org.springframework.data.repository.ListCrudRepository;

public interface EnrollmentRepository extends ListCrudRepository<Enrollment, Long> {
}
