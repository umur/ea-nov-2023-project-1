package com.alumni.EventService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.EventService.entity.UsersAttendingEvents;

@Repository
public interface UsersAttendingEventsRepository extends ListCrudRepository<UsersAttendingEvents, Long> {

}
