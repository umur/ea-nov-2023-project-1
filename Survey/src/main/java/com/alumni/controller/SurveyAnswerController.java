package com.alumni.controller;

import com.alumni.dto.SurveyAnswerDto;
import com.alumni.service.SurveyAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyAnswer")
@RequiredArgsConstructor
public class SurveyAnswerController {
    private final SurveyAnswerService surveyAnswerService;

    @PostMapping("/save")
    public void save(@RequestBody SurveyAnswerDto surveyAnswerDto){
        surveyAnswerService.save(surveyAnswerDto);
    }

    @GetMapping
    public List<SurveyAnswerDto> findAll(){
        return surveyAnswerService.findAll();
    }

    @GetMapping("/{id}")
    public SurveyAnswerDto findById(@PathVariable Long id){
        return surveyAnswerService.findById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody SurveyAnswerDto surveyAnswerDto){
        surveyAnswerService.update(id, surveyAnswerDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        surveyAnswerService.deleteById(id);
    }
}
