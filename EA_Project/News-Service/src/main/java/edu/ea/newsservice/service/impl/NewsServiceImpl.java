package edu.ea.newsservice.service.impl;

import edu.ea.newsservice.model.News;
import edu.ea.newsservice.repository.NewsRepo;
import edu.ea.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepo newsRepo;
    @Override
    public void add(News news) {
        newsRepo.save(news);
    }

    @Override
    public void remove(int id) {
        News news = get(id);
        newsRepo.delete(news);
    }

    @Override
    public List<News> findAll() {
        return newsRepo.findAll();
    }

    @Override
    public void update(News news) {
        News news0 = get(news.getId());
        newsRepo.save(news);


    }

    @Override
    public News get(int id) {
        Optional<News> news = newsRepo.findById(id);
        if(news.isEmpty()){
            throw new IllegalArgumentException();
        }
        return news.get();
    }

	
}
