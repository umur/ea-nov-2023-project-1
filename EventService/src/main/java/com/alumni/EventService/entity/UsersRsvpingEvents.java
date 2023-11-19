package com.alumni.EventService.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users_rsvping_events")
public class UsersRsvpingEvents {

    @EmbeddedId
    private UsersRsvpingEventsId usersRsvpingEventsId;

    @ManyToOne
    @MapsId("eventId")
    private Event event;
}
