package com.project.alumni.dto.Job;

import java.time.LocalDateTime;

import com.project.alumni.entity.Job.Job;
import com.project.alumni.entity.Job.PostingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingDto {
    private Long id;

    private LocalDateTime creationDate;
    private PostingStatus status;

    private JobDto job;
    private Long posterId;

}
