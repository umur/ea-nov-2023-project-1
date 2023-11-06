package com.ea.project.dto;

import com.ea.project.entity.Announcement;
import com.ea.project.entity.Information;
import com.ea.project.entity.New;
import com.ea.project.entity.Replay;
import org.modelmapper.ModelMapper;

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

    public Information getInformation(ModelMapper modelMapper) {

        return modelMapper.map(this, New.class);
    }
}
