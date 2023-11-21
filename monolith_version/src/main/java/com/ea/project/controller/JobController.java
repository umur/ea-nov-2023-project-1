package com.ea.project.controller;

import com.ea.project.entity.Job;
import com.ea.project.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @PostMapping
    public Job addJob(@RequestBody Job job)
    {
        System.out.println("post job "+job);
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

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id)
    {
        System.out.println("delete");
        jobService.deleteJob(id);
    }
    @PatchMapping("/{id}")
    public void partialUpdateJob(@PathVariable Long id ,@RequestBody Job job)
    {
        jobService.partialupdateJob(id,job);
    }
    @PutMapping("/{id}")
    public void updateJob(@PathVariable Long id ,@RequestBody Job job)
    {
        jobService.updateJob(id,job);
    }
}
