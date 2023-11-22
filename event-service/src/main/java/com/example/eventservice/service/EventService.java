package com.example.eventservice.service;

import com.example.eventservice.dto.EventDto;
import com.example.eventservice.entity.Event;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventService {
    void create(EventDto eventDto);

    List<EventDto> findAll();

    EventDto findById(int id);

    void delete(int id);

    void update(EventDto eventDto);

    List<EventDto> findAllByNameContains(String keyword);
}
