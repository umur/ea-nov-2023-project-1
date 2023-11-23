package com.project.event.service;

import com.project.event.dto.get.GetFullEventDto;
import com.project.event.dto.post.PostFullEventDto;
import com.project.event.model.Event;
import com.project.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<GetFullEventDto> getAll() {
        var result = eventRepository.findAll();
        List<GetFullEventDto> eventDtos = new ArrayList<>();
        result.forEach(event -> eventDtos.add(modelMapper.map(event, GetFullEventDto.class)));
        return eventDtos;
    }

    @Override
    public GetFullEventDto getById(long id) {
        var result = eventRepository.findById(id);
        return modelMapper.map(result, GetFullEventDto.class);
    }

    @Override
    public GetFullEventDto addEvent(PostFullEventDto postFullEventDto) {
        var result = eventRepository.save(modelMapper.map(postFullEventDto, Event.class));
        return modelMapper.map(result, GetFullEventDto.class);
    }

    @Override
    public GetFullEventDto updateById(long id, PostFullEventDto postFullEventDto) {
       var event = modelMapper.map(postFullEventDto, Event.class);
       event.setId(id);
       var result = eventRepository.save(event);
       return modelMapper.map(result, GetFullEventDto.class);

    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}
