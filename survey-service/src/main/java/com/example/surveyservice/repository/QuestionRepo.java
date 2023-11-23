package com.example.surveyservice.repository;

import com.example.surveyservice.entity.Question;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends ListCrudRepository<Question,Integer> {
}
