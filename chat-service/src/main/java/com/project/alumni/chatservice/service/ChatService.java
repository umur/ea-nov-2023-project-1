package com.project.alumni.chatservice.service;

import com.project.alumni.chatservice.dto.ChatDto;
import com.project.alumni.chatservice.dto.ChatParticipantDto;

import java.util.List;


public interface ChatService {

    List<ChatDto> getAllChats();

    ChatDto getChatsById(Long id);

    ChatDto addChat(ChatDto chatDto);

    ChatDto updateChat(Long id, ChatDto chatDto);

    void deleteChat(Long id);

    List<ChatParticipantDto> getAllChatParticipants(Long chatId);


    List<ChatParticipantDto> addChatParticipants(Long chatId, List<Long> userIds);

    void deleteChatParticipant(Long chatId, Long userId);

}
