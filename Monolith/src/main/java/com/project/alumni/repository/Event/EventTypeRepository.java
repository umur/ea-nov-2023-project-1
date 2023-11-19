package com.project.alumni.repository.Event;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.alumni.entity.Event.EventType;

@Repository
public interface EventTypeRepository extends ListCrudRepository<EventType, Long> {

}
