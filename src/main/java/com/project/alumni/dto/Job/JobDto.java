package com.project.alumni.dto.Job;

import java.util.List;

import com.project.alumni.entity.User;
import com.project.alumni.entity.Job.Application;
import com.project.alumni.entity.Job.Posting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {
    private Long id;

    private String name;

    private String description;
    private List<String> requirements;

    private Long userId;
}
