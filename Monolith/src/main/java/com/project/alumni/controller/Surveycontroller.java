package com.project.alumni.controller;

import com.project.alumni.dto.SurveyDto;
import com.project.alumni.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class Surveycontroller {
    private final SurveyService surveyService;

    @PostMapping("/save")
    public void save(@RequestBody SurveyDto surveyDto){
        surveyService.save(surveyDto);
    }
    @GetMapping
    public List<SurveyDto> findAll(){
        return surveyService.findAll();
    }
    @GetMapping("/{id}")
    public SurveyDto findById(@PathVariable Long id){
        return surveyService.findById(id);
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody SurveyDto surveyDto){
        surveyService.update(id, surveyDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        surveyService.delete(id);
    }
}
