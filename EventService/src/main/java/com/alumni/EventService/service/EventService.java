package com.alumni.EventService.service;

import com.alumni.EventService.dto.EventDto;

public interface EventService extends CrudService<EventDto> {
    public void addOrganizer(Long userId, Long EventId);

    public void addAttendee(Long userId, Long EventId);

    public void addRsvper(Long userId, Long EventId);

}
