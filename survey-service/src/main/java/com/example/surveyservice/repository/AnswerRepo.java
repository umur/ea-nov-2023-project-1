package com.example.surveyservice.repository;

import com.example.surveyservice.entity.Answer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends ListCrudRepository<Answer , Integer> {
}
