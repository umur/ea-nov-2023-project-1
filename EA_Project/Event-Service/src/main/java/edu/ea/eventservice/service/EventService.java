package edu.ea.eventservice.service;


import edu.ea.eventservice.model.Event;
import edu.ea.eventservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EventService {
    public void add(Event event);
    public void remove(int id);

    public void update(Event event);

    public void RSVP (int id, Student student);

    List<Event> findAll();
	public Event getById(int id);
}
