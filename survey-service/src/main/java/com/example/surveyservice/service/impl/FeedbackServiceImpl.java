package com.example.surveyservice.service.impl;

import com.example.surveyservice.dto.FeedbackDto;
import com.example.surveyservice.entity.Answer;
import com.example.surveyservice.entity.Feedback;
import com.example.surveyservice.repository.AnswerRepo;
import com.example.surveyservice.repository.FeedbackRepo;
import com.example.surveyservice.repository.QuestionRepo;
import com.example.surveyservice.repository.SurveyRepo;
import com.example.surveyservice.service.AnswerService;
import com.example.surveyservice.service.FeedbackService;
import com.example.surveyservice.service.QuestionService;
import com.example.surveyservice.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepo feedbackRepo;
    private final ModelMapper modelMapper;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final SurveyService surveyService;
    @Override
    public Feedback save(FeedbackDto feedbackDto) {

//        Feedback feedback=modelMapper.map(feedbackDto , Feedback.class);
//        feedbackRepo.save(feedback);
//        System.out.println("in save: "+ feedback.toString());
        Feedback feedback=new Feedback();
        feedback.setSurvey(surveyService.getById(feedbackDto.getSurveyId()));
        feedback.setQuestion(questionService.getById(feedbackDto.getQuestionAnswers().get(0).getQuestionId()));
        Answer answer= new Answer();
        answer.setContent(feedbackDto.getQuestionAnswers().get(0).getAnswer().getContent());
        answer.setQuestion(questionService.getById(feedbackDto.getQuestionAnswers().get(0).getQuestionId()));
        feedback.setAnswer(answer);
        System.out.println("first feedback: "+feedback.toString());
        return null;
    }



    @Override
    public List<Feedback> getAll() {
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback getById(int id) {
        return feedbackRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(FeedbackDto feedbackDto) {
        Feedback feedback=modelMapper.map(feedbackDto , Feedback.class);
        feedbackRepo.delete(feedback);
    }


}
