package com.alumni.EventService.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
    private Long id;

    private String name;
    private String location;

    private LocalDateTime eventDate;

    private EventTypeDto type;

    private List<UsersOrganizingEventsDto> organizers = new ArrayList<>();
    private List<UsersAttendingEventsDto> attendees = new ArrayList<>();
    private List<UsersRsvpingEventsDto> rsvpers = new ArrayList<>();

}
