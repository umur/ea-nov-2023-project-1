package com.project.event.dto.post;

import com.project.event.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFullAlumniEventDto {
    private Event event;
    private boolean isAttended;
    private boolean isAccepted;
}
