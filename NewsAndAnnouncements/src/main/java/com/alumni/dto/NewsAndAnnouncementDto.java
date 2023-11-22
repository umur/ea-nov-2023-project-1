package com.alumni.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsAndAnnouncementDto {

    private Long id;

    private String type;

    private String title;
    private String content;
    private Date publishDate;

}
