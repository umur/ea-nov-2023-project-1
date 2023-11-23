package com.alumni.jobservice.Controller;

import com.alumni.jobservice.Dto.ExperienceDTO;
import com.alumni.jobservice.Dto.JobDTO;
import com.alumni.jobservice.Entity.Experience;
import com.alumni.jobservice.Entity.Job;
import com.alumni.jobservice.Repo.ExperienceRepo;
import com.alumni.jobservice.Service.ExperienceService;
import com.alumni.jobservice.Service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final ExperienceService experienceService;

    @GetMapping("")
    public List<Job> getJobs(@RequestParam(required = false) String organization,
                             @RequestParam(required = false) String state,
                             @RequestParam(required = false) String city) {
        return jobService.findJobsByFilter(organization, state, city);
    }

    @PostMapping("")
    public void addJob(@RequestBody JobDTO job){
         jobService.saveJob(job);
    }

    @PostMapping("/experience")
    public Experience addExperience(@RequestBody ExperienceDTO experience){
        return experienceService.addExperience(experience);

    }

    @DeleteMapping("/experience/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Long id){
        return experienceService.deleteExperience(id);
    }

    @GetMapping("/experience/user/{id}")
    public List<Experience> getExperienceByProfileId(@PathVariable Long id){
        return experienceService.getExperiencesByProfileId(id);
    }

    @PutMapping("/experience/{id}")
    public ResponseEntity<?> editExperience(@PathVariable Long id, @RequestBody ExperienceDTO experience){
        return experienceService.editExperience(id, experience);
    }
}
