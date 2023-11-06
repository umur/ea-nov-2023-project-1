package com.project.alumni.service.chat;

import com.project.alumni.dto.chat.ChatDto;
import com.project.alumni.dto.UserFullDetailsDto;

import java.util.List;


public interface ChatService {

    List<ChatDto> getAllChats();

    ChatDto getChatsById(Long id);

    ChatDto addChat(ChatDto chatDto);

    ChatDto updateChat(Long id, ChatDto chatDto);

    void deleteChat(Long id);

    List<UserFullDetailsDto> getAllChatParticipants(Long chatId);


    List<UserFullDetailsDto> addChatParticipants(Long chatId, List<Long> userIds);

    void deleteChatParticipant(Long chatId, Long userId);

}
