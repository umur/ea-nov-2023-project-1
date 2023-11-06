package com.miu.alumnimanagementportal.controllers;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.dtos.UserDto;
import com.miu.alumnimanagementportal.services.JobPostService;
import com.miu.alumnimanagementportal.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobPosts")
public class JobPostController {
    private final JobPostService jobPostService;
    private final Converter converter;



    @PostMapping
    public ResponseEntity<?> createJobPost(@Valid @RequestBody JobPostDto jobPostDto) {
        jobPostService.create(jobPostDto);
        return converter.buildReposeEntity(Map.of("message", "Job Post created successfully"), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getJobPostAll() {
        return converter.buildReposeEntity(Map.of("data", jobPostService.findAll()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobPostById(@PathVariable Long id) {
        return converter.buildReposeEntity(Map.of("data", jobPostService.getjobPostById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobPostById(@Valid @PathVariable Long id, @RequestBody JobPostDto jobPostDto) {
        return converter.buildReposeEntity(Map.of("data", jobPostService.update(jobPostDto,id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPostById(@PathVariable Long id) {
        jobPostService.delete(id);
        return converter.buildReposeEntity(Map.of("message", "Job Post Deleted successfully"), HttpStatus.ACCEPTED);
    }




}
