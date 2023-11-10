package com.example.EA_project.service;
import com.example.EA_project.entity.Application;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {




    public Application addApplication(Application a);
    public List<Application> getAllApplications();


    public List<Application> getApplicationsOfStudent(Long id);
    public Application getApplicationById(Long id);

    public void deleteApplication(Long id, Long id2);

}
