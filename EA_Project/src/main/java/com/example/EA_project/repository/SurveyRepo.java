package com.example.EA_project.repository;

import com.example.EA_project.entity.Survey;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends ListCrudRepository<Survey, Integer> {

}
