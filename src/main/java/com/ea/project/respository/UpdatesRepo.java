package com.ea.project.respository;

import com.ea.project.entity.Replay;

import java.time.LocalDateTime;
import java.util.List;

public class UpdatesRepo {
    private int id;
    private String author;
    private String content;
    private LocalDateTime updateDateTime;

    private List<Replay> replays;
}
