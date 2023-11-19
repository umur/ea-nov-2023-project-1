package com.example.news.repository;


import com.example.news.model.News;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends ListCrudRepository<News,Integer> {
}
