package com.alumni.jobservice.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExperienceDTO {
    private Date startDate;
    private Date endDate;
    private Long jobId;
    private Long profileId;
}
