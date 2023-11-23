package com.example.eventService.service;

import com.example.eventService.dto.EventDto;
import com.example.eventService.entity.Event;

import java.util.List;

public interface EventService {
    Event create(Event event);

    List<EventDto> findAll();

    EventDto findById(int id);

    void delete(int id);

    void update(EventDto eventDto);

    List<EventDto> findAllByNameContains(String keyword);
}
