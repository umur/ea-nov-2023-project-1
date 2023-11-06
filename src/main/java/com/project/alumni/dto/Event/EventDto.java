package com.project.alumni.dto.Event;

import java.time.LocalDateTime;
import java.util.List;

import com.project.alumni.dto.UserMinimalDto;
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

    private List<UserMinimalDto> organizers;
    private List<UserMinimalDto> attendees;
    private List<UserMinimalDto> rsvpers;

}
