package edu.ea.eventservice.respository;

import edu.ea.eventservice.model.Event;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends ListCrudRepository<Event, Integer> {
}
