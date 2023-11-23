package com.example.surveyservice.service;

import com.example.surveyservice.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyService {
    public void save(Survey survey);
    public List<Survey> getAll();
    public Survey getById(int id);
    public boolean delete(Survey survey);
}
