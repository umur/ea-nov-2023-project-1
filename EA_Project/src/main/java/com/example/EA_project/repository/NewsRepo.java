package com.example.EA_project.repository;

import com.example.EA_project.entity.News;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends ListCrudRepository<News,Integer> {
}
