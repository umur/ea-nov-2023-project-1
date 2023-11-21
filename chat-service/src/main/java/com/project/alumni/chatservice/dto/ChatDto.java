package com.project.alumni.chatservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {

    private Long id;
    private Boolean isGroup;
    private String name;

}
