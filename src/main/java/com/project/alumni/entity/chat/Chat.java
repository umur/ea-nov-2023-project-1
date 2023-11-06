package com.project.alumni.entity.chat;

import com.project.alumni.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isGroup = false;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> participants;

    @OneToMany(mappedBy = "chat")
    private List<ChatMessage> messages;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
