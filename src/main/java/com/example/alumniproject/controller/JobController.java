package com.example.alumniproject.controller;

import com.example.alumniproject.entity.Job;
import com.example.alumniproject.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    @GetMapping("")
    public List<Job> getJobs(@RequestParam(required = false) String organization,
                             @RequestParam(required = false) String state,
                             @RequestParam(required = false) String city) {
        return jobService.findJobsByFilter(organization, state, city);
    }

}
