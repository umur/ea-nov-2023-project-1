package com.alumni.repository;

import com.alumni.entity.SurveyAnswer;
import org.springframework.data.repository.ListCrudRepository;

public interface SurveyAnswerRpo extends ListCrudRepository<SurveyAnswer, Long> {
}
