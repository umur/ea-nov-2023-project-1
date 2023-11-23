package com.example.surveyservice.service;

import com.example.surveyservice.entity.Answer;
import com.example.surveyservice.entity.Survey;
import com.example.surveyservice.repository.AnswerRepo;

import java.util.List;

public interface AnswerService {
    public void save(Answer answer);
    public List<Answer> getAll();
    public Answer getById(int id);
    public boolean delete(Answer answer);
}
