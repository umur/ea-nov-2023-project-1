package com.project.alumni.repository;

import com.project.alumni.entity.Chat;
import com.project.alumni.entity.ChatMessage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatsMessagesRepository extends ListCrudRepository<ChatMessage, Long> {


    Optional<ChatMessage> findByIdAndDeletedAtIsNull(Long messageId);
}
