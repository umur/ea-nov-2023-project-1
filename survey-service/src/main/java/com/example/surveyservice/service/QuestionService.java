package com.example.surveyservice.service;

import com.example.surveyservice.entity.Question;
import com.example.surveyservice.entity.Survey;

import java.util.List;

public interface QuestionService {
    public void save(Question question);
    public List<Question> getAll();
    public Question getById(int id);
    public boolean delete(Question question);
}
