package com.miu.alumnimanagementportal.services;


import com.miu.alumnimanagementportal.dtos.EducationDetailsDto;
import com.miu.alumnimanagementportal.dtos.EventDto;

import java.util.List;

public interface EventService {
    List<EventDto> findAll();
    void create(EventDto eventDto);


    EventDto update(EventDto eventDto, Long id);

    EventDto getEventById(Long id);

    void delete(Long id);
}
