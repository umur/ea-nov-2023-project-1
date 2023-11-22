package edu.ea.eventservice.service.impl;


import edu.ea.eventservice.model.Event;
import edu.ea.eventservice.respository.EventRepo;
import edu.ea.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;
    private final JwtService jwtService;
    @Override
    public void add(Event event) {
        event.setUserId(jwtService.getUserIdFromToken());
        eventRepo.save(event);
    }

    @Override
    public void remove(int id) {
        Optional<Event> event = eventRepo.findById(id);
        if(event.isEmpty()){
            throw new IllegalArgumentException();
        }
        else{
            eventRepo.delete(event.get());
        }

    }



    @Override
    public void update(Event event) throws Exception {
       if(event.getUserId()!= jwtService.getUserIdFromToken())
           throw  new Exception("Only event owner can update the event");
        eventRepo.save(event);
    }

    @Override
    public void RSVP(int id) throws Exception {
        Optional<Event> eventO = eventRepo.findById(id);
        if(eventO.isEmpty())
          throw  new Exception("Event not found");

            Event event = eventO.get();
            event.getRSVP().add(jwtService.getUserIdFromToken());
            update(event);

    }

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

	@Override
	public Event getById(int id) throws Exception {
		Optional<Event> event = eventRepo.findById(id);
		if(event.isEmpty())
            throw new Exception("Event not found");
		return event.get();
	}
}
