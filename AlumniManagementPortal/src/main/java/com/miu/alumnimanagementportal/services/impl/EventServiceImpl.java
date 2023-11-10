package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.EventDto;
import com.miu.alumnimanagementportal.entities.Attendant;
import com.miu.alumnimanagementportal.entities.Event;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.AttendantRepository;
import com.miu.alumnimanagementportal.repositories.EventRepository;
import com.miu.alumnimanagementportal.services.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final Converter converter;
    private final ModelMapper modelMapper;

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream()
                .map(element -> converter.convert(element, EventDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(EventDto eventDto) {
        Optional.ofNullable(eventDto.getId()).ifPresent(id -> {
            if (eventRepository.existsById(id)) {
                throw new DataAlreadyExistException("EventRepository with id " + id + " already exists");
            }
        });
        eventRepository.save(converter.convert(eventDto, Event.class));
    }

    @Override
    public EventDto update(EventDto eventDto, Long id) {
        return Optional.ofNullable(eventDto.getId()).map(entityId -> {
            if (!eventRepository.existsById(entityId)) {
                throw new ResourceNotFoundException("Event with id " + entityId + " not found");
            }
            return converter.convert(eventRepository.save(converter.convert(eventDto, Event.class)), EventDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));
    }

    @Override
    public EventDto getEventById(Long id) {
        return Optional.ofNullable(id)
                .map(eventRepository::findById)
                .map(element -> converter.convert(element, EventDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }
        eventRepository.deleteById(id);
    }
}
