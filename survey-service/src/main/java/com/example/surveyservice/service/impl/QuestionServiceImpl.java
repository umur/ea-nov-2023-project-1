package com.example.surveyservice.service.impl;

import com.example.surveyservice.entity.Question;
import com.example.surveyservice.repository.QuestionRepo;
import com.example.surveyservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepo;
    @Override
    public void save(Question question) {
        questionRepo.save(question);
    }

    @Override
    public List<Question> getAll() {
        return questionRepo.findAll();
    }

    @Override
    public Question getById(int id) {
        return questionRepo.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Question question) {
        return false;
    }
}
