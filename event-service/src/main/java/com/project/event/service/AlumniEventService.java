package com.project.event.service;

import com.project.event.dto.get.GetFullAlumniEventDto;
import com.project.event.dto.post.PostFullAlumniEventDto;

import java.util.List;

public interface AlumniEventService {
    public List<GetFullAlumniEventDto> getAllAlumniEvents();
    public GetFullAlumniEventDto getAlumniEventById(long alumniId, long eventId);
    public GetFullAlumniEventDto saveAlumniEvent(PostFullAlumniEventDto alumniEventDto);
    public GetFullAlumniEventDto updateAlumniEvent(long alumniId, long eventId, PostFullAlumniEventDto alumniEventDto);
    public void deleteAlumniEvent(long alumniId, long eventId);
}
