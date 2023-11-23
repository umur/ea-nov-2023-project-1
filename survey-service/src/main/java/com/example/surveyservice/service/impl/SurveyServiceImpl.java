package com.example.surveyservice.service.impl;

import com.example.surveyservice.entity.Question;
import com.example.surveyservice.entity.Survey;
import com.example.surveyservice.repository.SurveyRepo;
import com.example.surveyservice.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepo surveyRepo;
    @Override
    public void save(Survey survey) {
        if (survey.getQuestions() != null) {
            for (Question question : survey.getQuestions()) {
                question.setSurvey(survey);
            }
        }
        surveyRepo.save(survey);
    }

    @Override
    public List<Survey> getAll() {
        return surveyRepo.findAll();
    }

    @Override
    public Survey getById(int id) {
        return surveyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Survey survey) {

        if(getById(survey.getId()) !=null)
        {
            surveyRepo.delete(survey);
            return true;
        }
        return false;
    }
}
