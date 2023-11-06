package com.project.alumni.service.chat;

import com.project.alumni.dto.chat.ChatDto;
import com.project.alumni.dto.UserLoginDto;

import java.util.List;


public interface ChatService {

    List<ChatDto> getAllChats();

    ChatDto getChatsById(Long id);

    ChatDto addChat(ChatDto chatDto);

    ChatDto updateChat(Long id, ChatDto chatDto);

    void deleteChat(Long id);

    List<UserLoginDto> getAllChatParticipants(Long chatId);


    List<UserLoginDto> addChatParticipants(Long chatId, List<Long> userIds);

    void deleteChatParticipant(Long chatId, Long userId);

}
