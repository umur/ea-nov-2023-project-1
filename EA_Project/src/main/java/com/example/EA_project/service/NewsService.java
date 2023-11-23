package com.example.EA_project.service;

import com.example.EA_project.entity.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    public void add(News news);
    public void remove(int id);
    public List<News> findAll();
    public void update(News news);

    public News get(int id);

}
