package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.NewsDto;
import com.miu.alumnimanagementportal.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public void create(NewsDto newsDto) {

    }

    @Override
    public List<NewsDto> findAll() {
        return null;
    }

    @Override
    public NewsDto update(NewsDto newsDto, Long id) {
        return null;
    }

    @Override
    public NewsDto getNewsById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
