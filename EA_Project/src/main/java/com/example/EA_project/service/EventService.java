package com.example.EA_project.service;

import com.example.EA_project.entity.Event;
import com.example.EA_project.entity.Student;
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
