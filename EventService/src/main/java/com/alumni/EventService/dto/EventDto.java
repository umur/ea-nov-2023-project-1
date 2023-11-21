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

    // private List<UserFullDetailsDto> organizers = new ArrayList<>();
    // private List<UserFullDetailsDto> attendees = new ArrayList<>();
    // private List<UserFullDetailsDto> rsvpers = new ArrayList<>();

}
