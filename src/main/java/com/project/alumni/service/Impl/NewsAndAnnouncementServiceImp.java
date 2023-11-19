package com.project.alumni.service.Impl;

import com.project.alumni.dto.NewsAndAnnouncementDto;
import com.project.alumni.entity.NewsAndAnnouncements;
import com.project.alumni.repository.NewsAndAnnouncementsRepo;
import com.project.alumni.service.NewsAndAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsAndAnnouncementServiceImp implements NewsAndAnnouncementService {
    private final NewsAndAnnouncementsRepo newsAndAnnouncementsRepo;
    private final ModelMapper modelMapper;

    @Override
    public void save(NewsAndAnnouncementDto newsAndAnnouncementDto) {
        newsAndAnnouncementsRepo.save(
                modelMapper.map(newsAndAnnouncementDto, NewsAndAnnouncements.class));
    }

    @Override
    public List<NewsAndAnnouncementDto> findAll() {
        List<NewsAndAnnouncements> newsAndAnnouncements = newsAndAnnouncementsRepo.findAll();
        var res = new ArrayList<NewsAndAnnouncementDto>();
        newsAndAnnouncements.forEach(e ->{
            if (e.getDeleted() == 0) {
                res.add(modelMapper.map(e, NewsAndAnnouncementDto.class));
            }
        });
        return res;
    }

    @Override
    public NewsAndAnnouncementDto findById(Long id) {
        var res = newsAndAnnouncementsRepo.findById(id);
        if (!res.isPresent() || res.get().getDeleted() == 1){
            return null;
        }
        return modelMapper.map(res, NewsAndAnnouncementDto.class);
    }

    @Override
    public void update(Long id, NewsAndAnnouncementDto newsAndAnnouncementDto) {
        var newsAndAnnouncements = newsAndAnnouncementsRepo.findById(id);
        if(newsAndAnnouncements.isPresent() && newsAndAnnouncements.get().getDeleted() == 0){
            NewsAndAnnouncements var1 = modelMapper.map(newsAndAnnouncementDto, NewsAndAnnouncements.class);
            var1.setId(id);
            var1.setTitle(newsAndAnnouncementDto.getTitle());
            var1.setContent(newsAndAnnouncementDto.getContent());
            var1.setPublishDate(newsAndAnnouncementDto.getPublishDate());
            newsAndAnnouncementsRepo.save(var1);
        }
    }

    @Override
    public void delete(Long id) {
        var newsAndAnnouncements = newsAndAnnouncementsRepo.findById(id);
        if(newsAndAnnouncements.isPresent()){
            newsAndAnnouncements.get().setDeleted(1);
        }
    }
}
