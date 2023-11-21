package com.alumni.controller;

import com.alumni.dto.SurveyDto;
import com.alumni.service.SurveyService;
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
