package com.alumni.EventService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.EventService.entity.UsersRsvpingEvents;

@Repository
public interface UsersRsvpingEventsRepository extends ListCrudRepository<UsersRsvpingEvents, Long> {

}
