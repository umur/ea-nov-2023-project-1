package com.miu.alumnimanagementportal.dtos;

import com.miu.alumnimanagementportal.common.enums.EventType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO for {@link com.miu.alumnimanagementportal.entities.Event}
 */
@Value
public class EventDto implements Serializable {
    Long id;
    Long version;
    Date createdDate;
    Date lastModifiedDate;
    String title;
    String description;
    EventType eventType;
    String location;
    Date staringDate;
    LocalDateTime endingDate;
}