package com.project.alumni.service.chat;


import com.project.alumni.dto.chat.ChatMessagesDto;

import java.util.List;

public interface ChatMessagesService {

    List<ChatMessagesDto> getAllMessages(Long chatId);

    ChatMessagesDto getMessage(Long chatId, Long messageId);

    ChatMessagesDto addMessage(Long chatId, ChatMessagesDto messageDto);

    ChatMessagesDto updateMessage(Long chatId, Long messageId, ChatMessagesDto messageDto);

    void deleteMessage(Long chatId, Long messageId);

}
