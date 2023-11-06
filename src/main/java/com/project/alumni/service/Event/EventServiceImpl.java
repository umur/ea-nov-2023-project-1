package com.project.alumni.service.Event;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.alumni.dto.UserMinimalDto;
import com.project.alumni.dto.Event.EventDto;
import com.project.alumni.entity.Event.Event;
import com.project.alumni.repository.UserRepository;
import com.project.alumni.repository.Event.EventRepository;
import com.project.alumni.repository.Event.EventTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepo;
    private final EventTypeRepository eventTypeRepo;
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    @Override
    public void save(EventDto eventDto) {

        var event = eventRepo.save(mapper.map(eventDto, Event.class));

        eventDto.getOrganizers().forEach(organizer -> {
            addOrganizer(organizer.getId(), event.getId());
        });

        eventDto.getAttendees().forEach(attendee -> {
            addAttendee(attendee.getId(), event.getId());
        });
        eventDto.getRsvpers().forEach(rsvper -> {
            addRsvper(rsvper.getId(), event.getId());
        });

    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepo.findAll();
        var res = new ArrayList<EventDto>();
        events.forEach(event -> {
            res.add(mapper.map(event, EventDto.class));
        });
        return res;
    }

    @Override
    public EventDto findById(Long id) {
        var res = eventRepo.findById(id);
        return mapper.map(res, EventDto.class);
    }

    @Override
    public void update(Long id, EventDto updatedEvent) {
        var dbEventOptional = eventRepo.findById(id);
        if (dbEventOptional.isPresent()) {
            var dbEvent = dbEventOptional.get();

            dbEvent.setEventDate(updatedEvent.getEventDate());
            dbEvent.setName(updatedEvent.getName());
            dbEvent.setLocation(updatedEvent.getLocation());

            var dbType = eventTypeRepo.findById(updatedEvent.getTypeId());
            if (dbType.isPresent()) {
                dbEvent.setType(dbType.get());
            }

            eventRepo.save(dbEvent);
        }
    }

    @Override
    public void addOrganizer(Long userId, Long EventId) {
        var event = eventRepo.findById(EventId);
        var user = userRepo.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        event.get().getOrganizers().add(user.get());
        user.get().getOrganized().add(event.get());
        eventRepo.save(event.get());
    }

    @Override
    public void addAttendee(Long userId, Long EventId) {
        var event = eventRepo.findById(EventId);
        var user = userRepo.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        event.get().getAttendees().add(user.get());
        user.get().getAttended().add(event.get());
        eventRepo.save(event.get());
    }

    @Override
    public void addRsvper(Long userId, Long EventId) {
        var event = eventRepo.findById(EventId);
        var user = userRepo.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        event.get().getRsvpers().add(user.get());
        user.get().getRsvped().add(event.get());
        eventRepo.save(event.get());
    }

    @Override
    public void delete(Long id) {
        eventRepo.deleteById(id);
    }
}
