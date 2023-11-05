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

import com.project.alumni.dto.Job.ApplicationDto;
import com.project.alumni.service.Job.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationsController {
    private final ApplicationService applicationService;

    @GetMapping
    private List<ApplicationDto> findAll() {
        return applicationService.findAll();
    }

    @GetMapping("{id}")
    private ApplicationDto findById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> save(@RequestBody ApplicationDto newJob) {
        applicationService.save(newJob);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody ApplicationDto updatedJob) {
        applicationService.update(id, updatedJob);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
