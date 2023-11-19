package com.project.alumni.dto.Job;

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
