package com.example.eventService.repository;

import com.example.eventService.entity.Event;
import org.springframework.data.repository.ListCrudRepository;

public interface EventRepository extends ListCrudRepository<Event,Integer> {
}
