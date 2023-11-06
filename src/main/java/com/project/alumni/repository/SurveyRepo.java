package com.project.alumni.repository;

import com.project.alumni.entity.Survey;
import org.springframework.data.repository.ListCrudRepository;

public interface SurveyRepo extends ListCrudRepository<Survey, Long>{
}
