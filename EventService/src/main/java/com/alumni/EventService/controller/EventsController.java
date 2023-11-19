package com.alumni.EventService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.EventService.dto.EventDto;
import com.alumni.EventService.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventService eventService;

    @GetMapping
    private List<EventDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("{id}")
    private EventDto findById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> save(@RequestBody EventDto newEvent) {
        eventService.save(newEvent);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody EventDto updatedEvent) {
        eventService.update(id, updatedEvent);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/addOrganizer")
    private ResponseEntity<HttpStatus> addOrganizer(@PathVariable Long id, @RequestParam Long userId) {
        eventService.addOrganizer(userId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/addAttendee")
    private ResponseEntity<HttpStatus> addAttendee(@PathVariable Long id, @RequestParam Long userId) {
        eventService.addAttendee(userId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/addRsvper")
    private ResponseEntity<HttpStatus> addRsvper(@PathVariable Long id, @RequestParam Long userId) {
        eventService.addRsvper(userId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
