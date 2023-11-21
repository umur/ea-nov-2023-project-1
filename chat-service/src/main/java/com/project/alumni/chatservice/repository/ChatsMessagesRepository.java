package com.project.alumni.chatservice.repository;

import com.project.alumni.chatservice.entity.ChatMessage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatsMessagesRepository extends ListCrudRepository<ChatMessage, Long> {


    Optional<ChatMessage> findByIdAndDeletedAtIsNull(Long messageId);
}
