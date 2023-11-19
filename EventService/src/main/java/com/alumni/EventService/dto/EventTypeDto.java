package com.alumni.EventService.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTypeDto {
    private Long id;

    private String name;

    private List<EventDto> events = new ArrayList<>();
}