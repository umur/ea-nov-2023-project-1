package com.example.EA_project.service;

import com.example.EA_project.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public void add(Job job);
    public void remove(int id);
    public List<Job> finalAll();
    public void update(Job job);
}
