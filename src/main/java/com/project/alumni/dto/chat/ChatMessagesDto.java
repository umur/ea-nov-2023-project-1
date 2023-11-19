package com.project.alumni.dto.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.alumni.dto.user.UserFullDetailsDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessagesDto {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserFullDetailsDto sender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    private String type = "text";

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
