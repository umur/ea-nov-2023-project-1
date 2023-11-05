package com.project.alumni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alumni.dto.Job.JobDto;
import com.project.alumni.service.Job.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    private List<JobDto> findAll() {
        return jobService.findAll();
    }

    @GetMapping("{id}")
    private JobDto findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> save(@RequestBody JobDto newJob) {
        jobService.save(newJob);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody JobDto updatedJob) {
        jobService.update(id, updatedJob);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
