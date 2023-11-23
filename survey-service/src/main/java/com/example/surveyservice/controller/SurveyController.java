package com.example.surveyservice.controller;

import com.example.surveyservice.entity.Survey;
import com.example.surveyservice.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public void addSurvey(@RequestBody Survey survey){
        System.out.println("survey : "+survey.toString());
        surveyService.save(survey);
    }

    @GetMapping
    public List<Survey> getAll(){
        return surveyService.getAll();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable int id)
    {
        return surveyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSurvey(@PathVariable int id){
        if(surveyService.delete(surveyService.getById(id))){
            return "Deleted Successfully";
        }
        return "Could not delete";
    }
}
