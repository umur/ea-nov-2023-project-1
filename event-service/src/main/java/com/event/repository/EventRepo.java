package com.event.repository;

import com.event.entity.Event;
import org.springframework.data.repository.ListCrudRepository;

public interface EventRepo extends ListCrudRepository<Event,Integer> {
}
