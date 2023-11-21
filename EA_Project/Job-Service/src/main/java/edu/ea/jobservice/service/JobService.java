package com.example.EA_project.service;

import com.example.EA_project.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    public void add(Job job);
    public void remove(int id);
    public void update(Job job);

    public void apply(int jobId, int studentId);

    public List<Job> getByCity(String city);

    public List<Job> getByState(String state);

    public List<Job> getByCompanyName(String companyName);

    List<Job> findAll();
	public Job findById(int id);

}
