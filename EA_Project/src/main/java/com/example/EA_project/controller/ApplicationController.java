package com.example.EA_project.controller;


import com.example.EA_project.entity.Application;
import com.example.EA_project.service.impl.applicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    applicationServiceImpl applicationService;



    @GetMapping
    public List<Application> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return applications;
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        if (application != null) {
            return application;
        } else {
            return null;
        }
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsOfStudent(@PathVariable Long studentId) {
        return  applicationService.getApplicationsOfStudent(studentId);
    }


    @PostMapping
    public Application addApplication(@RequestBody Application application) {
        return applicationService.addApplication(application);
    }


    @GetMapping("/{id}/{adId}")
    public void deleteApplicationsOfStudent(@PathVariable Long id, @PathVariable Long adId) {
        applicationService.deleteApplication(id, adId);
    }
}
