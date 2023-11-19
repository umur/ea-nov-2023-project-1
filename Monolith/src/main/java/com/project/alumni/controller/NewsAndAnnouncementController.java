package com.project.alumni.controller;

import com.project.alumni.dto.NewsAndAnnouncementDto;
import com.project.alumni.service.NewsAndAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/newsAndAnnouncements")
public class NewsAndAnnouncementController{

    private final NewsAndAnnouncementService newsAndAnnouncementService;

    @PostMapping("/save")
    public void save(@RequestBody NewsAndAnnouncementDto newsAndAnnouncementDto){
        newsAndAnnouncementService.save(newsAndAnnouncementDto);
    }
    @GetMapping
    public List<NewsAndAnnouncementDto> findAll(){
        return newsAndAnnouncementService.findAll();
    }
    @GetMapping("/{id}")
    public NewsAndAnnouncementDto findById(@PathVariable Long id){
        return newsAndAnnouncementService.findById(id);
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody NewsAndAnnouncementDto newsAndAnnouncementDto){
        newsAndAnnouncementService.update(id, newsAndAnnouncementDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        newsAndAnnouncementService.delete(id);
    }
}
