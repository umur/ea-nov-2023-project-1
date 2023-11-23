package edu.ea.eventservice.service;


import edu.ea.eventservice.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EventService {
      void add(Event event);
      void remove(int id);

      void update(Event event) throws Exception;

      void RSVP (int id) throws Exception;

    List<Event> findAll();
	public Event getById(int id) throws Exception;
}
