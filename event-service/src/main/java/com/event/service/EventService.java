package com.event.service;

import com.event.dto.EventDto;

import java.util.List;

public interface EventService {
    void create(EventDto eventDto);

    List<EventDto> findAll();

    EventDto findById(int id);

    void delete(int id);

    void update(EventDto eventDto);

    List<EventDto> findAllByNameContains(String keyword);
}
