package com.alumni.EventService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.EventService.entity.Event;

@Repository
public interface EventRepository extends ListCrudRepository<Event, Long> {

}
