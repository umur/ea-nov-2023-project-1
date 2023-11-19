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

    // private EventTypeDto type;
    private Long typeId;

    private List<UserMinimalDto> organizers = new ArrayList<>();
    private List<UserMinimalDto> attendees = new ArrayList<>();
    private List<UserMinimalDto> rsvpers = new ArrayList<>();

}
