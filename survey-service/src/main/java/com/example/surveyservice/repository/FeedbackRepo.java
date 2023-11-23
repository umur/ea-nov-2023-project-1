package com.example.surveyservice.repository;

import com.example.surveyservice.entity.Feedback;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends ListCrudRepository<Feedback , Integer> {
}
