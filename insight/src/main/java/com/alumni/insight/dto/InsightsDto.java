package com.alumni.insight.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InsightsDto {
    private Long id;
    private String title;
    private String description;
    private Date publicationDate;
}
