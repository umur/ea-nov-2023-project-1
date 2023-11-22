package com.example.eventservice.service.impl;

import com.example.eventservice.dto.EventDto;
import com.example.eventservice.entity.Event;
import com.example.eventservice.repository.EventRepository;
import com.example.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    @Override
    public void create(Event event) {

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
