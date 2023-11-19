package com.alumni.EventService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.EventService.entity.EventType;

@Repository
public interface EventTypeRepository extends ListCrudRepository<EventType, Long> {

}
