package com.ea.project.controller;

import com.ea.project.entity.Job;
import com.ea.project.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @PostMapping
    public Job addJob(@RequestBody Job job)
    {
        System.out.println("post job");
        return jobService.save(job);
    }

    @GetMapping
    public List<Job> getAllJobs()
    {
        System.out.println("get all");
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable Long id)
    {
        System.out.println("get one with id "+id);
        return jobService.getJob(id);
    }
}
