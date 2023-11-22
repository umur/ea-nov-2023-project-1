package edu.ea.jobservice.controller;

import  edu.ea.jobservice.service.JobService;

import edu.ea.jobservice.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public List<Job> getJobs(){
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
    	return jobService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.add(job);
        return ResponseEntity.ok("Successfully added a job!");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) throws Exception {
        jobService.remove(id);
        return ResponseEntity.ok("Successfully deleted a job!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable int id) throws Exception {
        Job existingJob = jobService.findById(id);
        if (existingJob != null) {
            existingJob.setCompanyName(job.getCompanyName());
            existingJob.setIndustry(job.getIndustry());
            existingJob.setJobAddress(job.getJobAddress());
            jobService.update(existingJob);
            return ResponseEntity.ok("Successfully updated a job!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/apply/{jobId}")
    public ResponseEntity<String> applyToJob(@PathVariable int jobId) throws Exception {
        jobService.apply(jobId );
        return ResponseEntity.ok("Successfully applied a job!");
    }

    @GetMapping("/by-state")
    public List<Job> getJobByState(@RequestParam String state){
        return jobService.getByState(state);
    }

    @GetMapping("/by-city")
    public List<Job> getJobByCity(@RequestParam String city){
        return jobService.getByCity(city);
    }

    @GetMapping("/by-company-name")
    public List<Job> getJobByCompanyName(@RequestParam String companyName){
        return jobService.getByCompanyName(companyName);
    }


}
