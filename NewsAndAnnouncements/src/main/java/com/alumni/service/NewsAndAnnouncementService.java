package com.alumni.service;

import com.alumni.dto.NewsAndAnnouncementDto;

import java.util.List;

public interface NewsAndAnnouncementService {
    void save(NewsAndAnnouncementDto newsAndAnnouncementDto);
    List<NewsAndAnnouncementDto> findAll();
    NewsAndAnnouncementDto findById(Long id);
    void update(Long id,NewsAndAnnouncementDto newsAndAnnouncementDto);
    void delete(Long id);


}
