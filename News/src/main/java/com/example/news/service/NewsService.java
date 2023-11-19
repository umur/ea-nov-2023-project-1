package com.example.news.service;


import com.example.news.model.News;


import java.util.List;


public interface NewsService {
     void add(News news);
     void remove(int id);
     List<News> findAll();
     void update(News news);

     News get(int id);

}
