package com.project.event.dto.post;

import com.project.event.model.type.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFullEventDto {
private String description;
private EventType eventType;
}
