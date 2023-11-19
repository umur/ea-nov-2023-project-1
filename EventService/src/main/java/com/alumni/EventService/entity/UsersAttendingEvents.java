package com.alumni.EventService.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users_attending_events")
public class UsersAttendingEvents {

    @EmbeddedId
    private UsersAttendingEventsId usersAttendingEventsId;

    @ManyToOne
    @MapsId("eventId")
    private Event event;
}
