package com.project.alumni.repository;

import com.project.alumni.entity.SurveyAnswer;
import org.springframework.data.repository.ListCrudRepository;

public interface SurveyAnswerRpo extends ListCrudRepository<SurveyAnswer, Long> {
}
