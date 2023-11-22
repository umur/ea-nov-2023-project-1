package com.alumni.EventService.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_organizing_events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersOrganizingEvents {

    @EmbeddedId
    private UsersOrganizingEventsId usersOrganizingEventsId;

    @ManyToOne
    @MapsId("eventId")
    private Event event;

}
