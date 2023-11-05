package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.EventDto;
import com.miu.alumnimanagementportal.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<EventDto> findAll() {
        return null;
    }

    @Override
    public void create(EventDto eventDto) {

    }

    @Override
    public EventDto update(EventDto eventDto, Long id) {
        return null;
    }

    @Override
    public EventDto getEventById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
