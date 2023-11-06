package com.project.alumni.dto.Event;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTypeDto {
    private Long id;

    private String name;

    private List<EventDto> events;
}