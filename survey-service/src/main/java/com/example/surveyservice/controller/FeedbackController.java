package com.example.surveyservice.controller;

import com.example.surveyservice.dto.FeedbackDto;
import com.example.surveyservice.entity.Question;
import com.example.surveyservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public String getAll()
    {
        return "hello";
    }

    @PostMapping(produces = "application/json")
    public FeedbackDto createFeedback(@RequestBody FeedbackDto feedbackDto){

//        if (survey.getQuestions() != null) {
//            for (Question question : survey.getQuestions()) {
//                question.setSurvey(survey);
//            }
//        }

        System.out.println("feedbackDTO before save: "+feedbackDto.toString());
        feedbackService.save(feedbackDto);
        return feedbackDto;
    }
}
