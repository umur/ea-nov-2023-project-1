package com.project.alumni.service;

import com.project.alumni.dto.NewsAndAnnouncementDto;

import java.util.List;

public interface NewsAndAnnouncementService {
    void save(NewsAndAnnouncementDto newsAndAnnouncementDto);
    List<NewsAndAnnouncementDto> findAll();
    NewsAndAnnouncementDto findById(Long id);
    void update(Long id,NewsAndAnnouncementDto newsAndAnnouncementDto);
    void delete(Long id);


}
