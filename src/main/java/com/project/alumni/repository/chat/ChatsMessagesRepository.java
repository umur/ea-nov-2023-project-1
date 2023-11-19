package com.project.alumni.repository.chat;

import com.project.alumni.entity.chat.ChatMessage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatsMessagesRepository extends ListCrudRepository<ChatMessage, Long> {


    Optional<ChatMessage> findByIdAndDeletedAtIsNull(Long messageId);
}
