package com.alumni.EventService.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alumni.EventService.dto.EventDto;
import com.alumni.EventService.dto.UserFullDetailsDto;
import com.alumni.EventService.entity.Event;
import com.alumni.EventService.entity.UsersAttendingEvents;
import com.alumni.EventService.entity.UsersAttendingEventsId;
import com.alumni.EventService.entity.UsersOrganizingEvents;
import com.alumni.EventService.entity.UsersOrganizingEventsId;
import com.alumni.EventService.entity.UsersRsvpingEvents;
import com.alumni.EventService.entity.UsersRsvpingEventsId;
import com.alumni.EventService.repository.EventRepository;
import com.alumni.EventService.repository.EventTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepo;
    private final EventTypeRepository eventTypeRepo;
    private final ModelMapper mapper;
    private final UserServiceClient userServiceClient;

    @Override
    public void save(EventDto eventDto) {

        // eventDto.getOrganizers().forEach(organizer -> {
        // addOrganizer(organizer.getId(), eventDto.getId());
        // });

        // eventDto.getAttendees().forEach(attendee -> {
        // addAttendee(attendee.getId(), eventDto.getId());
        // });
        // eventDto.getRsvpers().forEach(rsvper -> {
        // addRsvper(rsvper.getId(), eventDto.getId());
        // });

        var event = mapper.map(eventDto, Event.class);
        eventRepo.save(event);
    }

    // private void populateEventWithOrganizers(Event event, EventDto eventDto) {
    // event.getOrganizers().forEach(org -> {
    // var userId = org.getUsersOrganizingEventsId().getOrganizerId();
    // var user = userServiceClient.getUserById(userId);
    // eventDto.getOrganizers().add(mapper.map(user, UserFullDetailsDto.class));
    // });
    // }

    // private void populateEventWithAttendees(Event event, EventDto eventDto) {
    // event.getAttendees().forEach(org -> {
    // var userId = org.getUsersAttendingEventsId().getAttendeeId();
    // var user = userServiceClient.getUserById(userId);
    // eventDto.getAttendees().add(mapper.map(user, UserFullDetailsDto.class));
    // });
    // }

    // private void populateEventWithRsvpers(Event event, EventDto eventDto) {
    // event.getRsvpers().forEach(org -> {
    // var userId = org.getUsersRsvpingEventsId().getRsvperId();
    // var user = userServiceClient.getUserById(userId);
    // eventDto.getRsvpers().add(mapper.map(user, UserFullDetailsDto.class));
    // });
    // }

    // public void populateEventWithUsers(Event event, EventDto eventDto) {
    // populateEventWithOrganizers(event, eventDto);
    // populateEventWithAttendees(event, eventDto);
    // populateEventWithRsvpers(event, eventDto);
    // }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepo.findAll();
        var res = new ArrayList<EventDto>();
        events.forEach(event -> {
            var eventDto = mapper.map(event, EventDto.class);

            // populateEventWithUsers(event, eventDto);

            res.add(eventDto);
        });
        return res;
    }

    @Override
    public EventDto findById(Long id) {
        var event = eventRepo.findById(id).get();
        var eventDto = mapper.map(event, EventDto.class);
        // populateEventWithUsers(event, eventDto);

        return eventDto;
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
    public void addOrganizer(Long userId, Long eventId) {
        var event = eventRepo.findById(eventId);
        var user = userServiceClient.getUserById(userId);

        if (event.isEmpty() || user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var userOrganizer = new UsersOrganizingEvents();
        userOrganizer.setEvent(event.get());
        userOrganizer.setUsersOrganizingEventsId(new UsersOrganizingEventsId(eventId, userId));

        event.get().getOrganizers().add(userOrganizer);
        eventRepo.save(event.get());
    }

    @Override
    public void addAttendee(Long userId, Long eventId) {
        var event = eventRepo.findById(eventId);
        var user = userServiceClient.getUserById(userId);

        if (event.isEmpty() || user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var userAttendee = new UsersAttendingEvents();
        userAttendee.setEvent(event.get());
        userAttendee.setUsersAttendingEventsId(new UsersAttendingEventsId(eventId, userId));

        event.get().getAttendees().add(userAttendee);
        eventRepo.save(event.get());
    }

    @Override
    public void addRsvper(Long userId, Long eventId) {
        var event = eventRepo.findById(eventId);
        var user = userServiceClient.getUserById(userId);

        if (event.isEmpty() || user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var userRsvper = new UsersRsvpingEvents();
        userRsvper.setEvent(event.get());
        userRsvper.setUsersRsvpingEventsId(new UsersRsvpingEventsId(eventId, userId));

        eventRepo.save(event.get());
    }

    @Override
    public void delete(Long id) {
        eventRepo.deleteById(id);
    }
}
