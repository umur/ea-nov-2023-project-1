package com.alumni.EventService.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersAttendingEventsId implements Serializable {
    private Long eventId;
    private Long attendeeId;
}
