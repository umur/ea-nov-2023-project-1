package com.alumni.repository;

import com.alumni.entity.Survey;
import org.springframework.data.repository.ListCrudRepository;

public interface SurveyRepo extends ListCrudRepository<Survey, Long>{
}
