package com.alumni.JobService.dto;

import java.time.LocalDateTime;

import com.alumni.JobService.entity.PostingStatus;

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
