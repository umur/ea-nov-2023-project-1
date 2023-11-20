package edu.ea.newsservice.controller;

import edu.ea.newsservice.model.News;
import edu.ea.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;


    @PostMapping
    public ResponseEntity<String> addNews(@RequestBody News news) {
        newsService.add(news);
        return ResponseEntity.ok("Successfully added news!");
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable int id) {
    	return newsService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNews(@RequestBody News news, @PathVariable int id){
        News existingNews = newsService.get(id);
        if (existingNews != null) {
            existingNews.setTitle(news.getTitle());
            existingNews.setBody(news.getBody());
            newsService.update(existingNews);
            return ResponseEntity.ok("Successfully updated news!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable int id){
        newsService.remove(id);
        return ResponseEntity.ok("Successfully deleted news!");
    }


}
