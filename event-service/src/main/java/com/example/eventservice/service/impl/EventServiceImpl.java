package com.example.eventService.service.impl;

import com.example.eventService.dto.EventDto;
import com.example.eventService.entity.Event;
import com.example.eventService.repository.EventRepository;
import com.example.eventService.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    //private final ModelMapper modelMapper;
    @Override
    public Event create(Event event) {
        eventRepository.save(event);
        System.out.println("impl"+event.toString());
        return event;
    }

    @Override
    public List<EventDto> findAll() {
        return null;
    }

    @Override
    public EventDto findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(EventDto eventDto) {

    }

    @Override
    public List<EventDto> findAllByNameContains(String keyword) {
        return null;
    }
}
