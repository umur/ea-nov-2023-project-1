package com.project.alumni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alumni.dto.Event.EventTypeDto;
import com.project.alumni.service.Event.EventTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventTypes")
@RequiredArgsConstructor
public class EventTypesController {
    private final EventTypeService eventTypeService;

    @GetMapping
    private List<EventTypeDto> findAll() {
        return eventTypeService.findAll();
    }

    @GetMapping("{id}")
    private EventTypeDto findById(@PathVariable Long id) {
        return eventTypeService.findById(id);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> save(@RequestBody EventTypeDto newEventType) {
        eventTypeService.save(newEventType);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody EventTypeDto updatedEventType) {
        eventTypeService.update(id, updatedEventType);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
