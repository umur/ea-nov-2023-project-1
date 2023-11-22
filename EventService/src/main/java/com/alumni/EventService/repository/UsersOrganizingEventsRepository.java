package com.alumni.EventService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.EventService.entity.UsersOrganizingEvents;

@Repository
public interface UsersOrganizingEventsRepository extends ListCrudRepository<UsersOrganizingEvents, Long> {

}
