package edu.ea.eventservice.service.impl;


import edu.ea.eventservice.model.Event;
import edu.ea.eventservice.model.Student;
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
    @Override
    public void add(Event event) {
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
    public void update(Event event) {
        remove(event.getId());
        add(event);
    }

    @Override
    public void RSVP(int id, Student student) {
        Optional<Event> eventO = eventRepo.findById(id);
        if(eventO.isEmpty()){
            throw new IllegalArgumentException();
        }
        else{
            Event event = eventO.get();
            event.getStudents();
            update(event);
        }
    }

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

	@Override
	public Event getById(int id) {
		Optional<Event> event = eventRepo.findById(id);
		if(event.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return event.get();
	}
}
