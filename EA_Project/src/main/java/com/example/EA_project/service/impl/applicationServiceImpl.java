package com.example.EA_project.service.impl;
import com.example.EA_project.entity.Application;
import com.example.EA_project.repository.ApplicationRepo;
import com.example.EA_project.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class applicationServiceImpl implements ApplicationService {


    private final ApplicationRepo apRepo;


    @Override
    public Application addApplication(Application a){
        return apRepo.save(a);
    }
    @Override
    public List<Application> getAllApplications(){
        return apRepo.findAll();
    }


    @Override
    public List<Application> getApplicationsOfStudent(Long id){
        return apRepo.getApplicationsByStudent_IdIsAndIsDeleted(id, false);
    }

    @Override

    public Application getApplicationById(Long id){
        return apRepo.getApplicationByAppIdIsAndIsDeleted(id, false);
    }

    @Override
    public void deleteApplication(Long id, Long id2){
        apRepo.deleteApplication(id, id2);
    }

}
