package com.example.surveyservice.service;

import com.example.surveyservice.dto.FeedbackDto;
import com.example.surveyservice.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    public Feedback save(FeedbackDto feedbackDto);
    public List<Feedback> getAll();
    public Feedback getById(int id);

    public void delete(FeedbackDto feedbackDto);
}
