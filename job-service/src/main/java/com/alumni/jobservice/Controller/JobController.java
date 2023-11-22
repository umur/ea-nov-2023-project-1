package com.alumni.jobservice.Controller;

import com.alumni.jobservice.Dto.JobDTO;
import com.alumni.jobservice.Entity.Job;
import com.alumni.jobservice.Service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public void addJob(HttpServletRequest request, @RequestBody JobDTO job){
         String userEmail = (String) request.getParameter("email");
         jobService.saveJob(userEmail, job);
    }
}
