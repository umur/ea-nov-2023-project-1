package com.miu.alumnimanagementportal.controllers;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.dtos.NewsDto;
import com.miu.alumnimanagementportal.services.JobPostService;
import com.miu.alumnimanagementportal.services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    private final Converter converter;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsDto newsDto) {
        newsService.create(newsDto);
        return converter.buildReposeEntity(Map.of("message", "News created successfully"), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getNewsAll() {
        return converter.buildReposeEntity(Map.of("data", newsService.findAll()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        return converter.buildReposeEntity(Map.of("data", newsService.getNewsById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNewsById(@Valid @PathVariable Long id, @RequestBody NewsDto newsDto) {
        return converter.buildReposeEntity(Map.of("data", newsService.update(newsDto,id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNewsById(@PathVariable Long id) {
        newsService.delete(id);
        return converter.buildReposeEntity(Map.of("message", "News Deleted successfully"), HttpStatus.ACCEPTED);
    }




}
