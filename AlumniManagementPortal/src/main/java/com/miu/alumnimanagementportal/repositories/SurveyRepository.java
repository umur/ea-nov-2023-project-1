package com.miu.alumnimanagementportal.repositories;

import com.miu.alumnimanagementportal.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}