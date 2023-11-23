package com.example.surveyservice.repository;

import com.example.surveyservice.entity.Survey;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends ListCrudRepository<Survey , Integer> {
}
