package com.alumni.jobservice.Dto;

import com.alumni.jobservice.Entity.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {

    private String title;

    private String description;

    private String organization;

    private Long assignerId;

    private Long posterId;

    private LocationDTO location;

}
