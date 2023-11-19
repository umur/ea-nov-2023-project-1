package com.project.alumni.controller;

import com.project.alumni.dto.InsightsDto;
import com.project.alumni.service.InsightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/insights")
public class InsightsController {
    private final InsightsService insightsService;

    @PostMapping("/save")
    public void save(@RequestBody InsightsDto insightsDto){
        insightsService.save(insightsDto);
    }
    @GetMapping
    public List<InsightsDto> findAll(){
        return insightsService.findAll();
    }

    @GetMapping("/{id}")
    public InsightsDto findById(@PathVariable Long id){
        return insightsService.findById(id);
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody InsightsDto insightsDto){
        insightsService.update(id, insightsDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        insightsService.delete(id);
    }
}
