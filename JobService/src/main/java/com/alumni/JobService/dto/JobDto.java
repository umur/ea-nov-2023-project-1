package com.alumni.JobService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {
    private Long id;

    private String name;

    private String description;
    private String requirements;

    private Long userId;
}
