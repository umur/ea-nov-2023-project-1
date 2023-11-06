package com.ea.project.dto;

import com.ea.project.entity.Replay;

import java.time.LocalDateTime;
import java.util.List;

public class InformationDto {
    private int id;
    private LocalDateTime updateDateTime;
    private String content;
    private String author;

    private String type;

    //announcement
    private LocalDateTime endDateTime;
    private String isUrgent;

    //updates
    private List<Replay> replays;

    //news
    private String title;
}
