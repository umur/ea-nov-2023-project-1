package com.project.event.service;

import com.project.event.dto.get.GetFullEventDto;
import com.project.event.dto.post.PostFullEventDto;

import java.util.List;

public interface EventService {
    List<GetFullEventDto> getAll();

    GetFullEventDto getById(long id);

    GetFullEventDto addEvent(PostFullEventDto postFullEventDto);

    GetFullEventDto updateById(long id, PostFullEventDto postFullEventDto);

    void deleteById(long id);

}
