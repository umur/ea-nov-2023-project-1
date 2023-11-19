package com.alumni.EventService.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alumni.EventService.dto.EventTypeDto;
import com.alumni.EventService.entity.EventType;
import com.alumni.EventService.repository.EventTypeRepository;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventTypeServiceImpl implements EventTypeService {
    private final EventTypeRepository eventTypeRepo;
    private final ModelMapper mapper;

    @Override
    public void save(EventTypeDto eventTypeDto) {
        eventTypeRepo.save(mapper.map(eventTypeDto, EventType.class));
    }

    @Override
    public List<EventTypeDto> findAll() {
        List<EventType> eventTypes = eventTypeRepo.findAll();
        var res = new ArrayList<EventTypeDto>();
        eventTypes.forEach(eventType -> {
            res.add(mapper.map(eventType, EventTypeDto.class));
        });
        return res;
    }

    @Override
    public EventTypeDto findById(Long id) {
        var res = eventTypeRepo.findById(id);
        return mapper.map(res, EventTypeDto.class);
    }

    @Override
    public void update(Long id, EventTypeDto updatedEventType) {
        var dbEventType = eventTypeRepo.findById(id);
        if (dbEventType.isPresent()) {
            dbEventType.get().setName(updatedEventType.getName());
            ;
            eventTypeRepo.save(dbEventType.get());
        }
    }

    @Override
    public void delete(Long id) {
        eventTypeRepo.deleteById(id);
    }
}
