package edu.ea.eventservice.controller;


import edu.ea.eventservice.model.Event;
import edu.ea.eventservice.model.Student;
import edu.ea.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;


    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.add(event);
        return ResponseEntity.ok("Successfully added an event!");
    }
    @PostMapping("/rsvp/{event_id}")
    public ResponseEntity<String> RSVPToAnEvent(@PathVariable int event_id, @RequestBody Student student){
        eventService.RSVP(event_id, student);
        return ResponseEntity.ok("Successfully RSVP to an event!");

    }
    @GetMapping
    public List<Event> getEvent() {
        return eventService.findAll();
    }
    
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
    	return eventService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@RequestBody Event event, @PathVariable int id){
        Event existingEvent = eventService.getById(id);
        if (existingEvent != null) {
            existingEvent.setName(event.getName());
            existingEvent.setDate(event.getDate());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setCategory(event.getCategory());
            existingEvent.setStudents(event.getStudents()); // Assuming the relationship is properly handled
            eventService.update(existingEvent);
            return ResponseEntity.ok("Successfully updated an event!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id){
        eventService.remove(id);
        return ResponseEntity.ok("Successfully deleted an event!");
    }

}
