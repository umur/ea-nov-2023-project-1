package com.project.alumni.dto.Job;

import com.project.alumni.entity.Job.ApplicationStatus;

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
