package com.example.surveyservice.service.impl;

import com.example.surveyservice.entity.Answer;
import com.example.surveyservice.repository.AnswerRepo;
import com.example.surveyservice.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepo answerRepo;
    @Override
    public void save(Answer answer) {
        answerRepo.save(answer);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepo.findAll();
    }

    @Override
    public Answer getById(int id) {
        return answerRepo.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Answer answer) {
        return false;
    }
}
