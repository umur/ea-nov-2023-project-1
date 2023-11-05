package com.miu.alumnimanagementportal.dtos;

import com.miu.alumnimanagementportal.common.enums.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    @NotBlank
    String title;

    String description;
    @NotNull
    EventType eventType;
    @NotNull
    @NotEmpty
    @NotBlank
    String location;
    @NotNull
    Date staringDate;
    @NotNull
    LocalDateTime endingDate;
}