package com.alumni.JobService.dto;

import com.alumni.JobService.entity.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDto {
    private Long id;

    private ApplicationStatus status;

    private Long userId;
    private Long jobId;
}
