package com.alumni.EventService.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users_organizing_events")
@Data
public class UsersOrganizingEvents {

    @EmbeddedId
    private UsersOrganizingEventsId usersOrganizingEventsId;

    @ManyToOne
    @MapsId("eventId")
    private Event event;

}
