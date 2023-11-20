package edu.ea.newsservice.service;

import edu.ea.newsservice.model.News;


import java.util.List;


public interface NewsService {
     void add(News news);
     void remove(int id);
     List<News> findAll();
     void update(News news);

     News get(int id);

}
