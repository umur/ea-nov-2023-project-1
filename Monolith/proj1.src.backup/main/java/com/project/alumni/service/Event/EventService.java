package com.project.alumni.service.Event;

import com.project.alumni.dto.Event.EventDto;
import com.project.alumni.service.CrudService;

public interface EventService extends CrudService<EventDto> {
    public void addOrganizer(Long userId, Long EventId);

    public void addAttendee(Long userId, Long EventId);

    public void addRsvper(Long userId, Long EventId);

}
