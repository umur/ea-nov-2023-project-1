package com.example.EA_project.controller;

import com.example.EA_project.entity.Survey;
import com.example.EA_project.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<String> addSurvey(@RequestBody Survey survey) {
        surveyService.add(survey);
        return ResponseEntity.ok("Successfully added survey");
    }

    @GetMapping
    public List<Survey> getSurveys() {
        return surveyService.findAll();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable int id) {
    	return surveyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable int id){
        surveyService.remove(id);
        return ResponseEntity.ok("Successfully deleted survey");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSurvey(@RequestBody Survey survey, @PathVariable int id){
        Survey existingSurvey = surveyService.getById(id);
        if (existingSurvey != null) {
            existingSurvey.setName(survey.getName());
            existingSurvey.setDescription(survey.getDescription());
            existingSurvey.setSurveyResponses(survey.getSurveyResponses());
            surveyService.update(existingSurvey);
            return ResponseEntity.ok("Successfully updated survey");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
