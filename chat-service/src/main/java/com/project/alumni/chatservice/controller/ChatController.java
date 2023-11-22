package com.project.alumni.chatservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.alumni.chatservice.dto.ChatDto;
import com.project.alumni.chatservice.dto.ChatParticipantDto;
import com.project.alumni.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;
    Logger log = LoggerFactory.getLogger(ChatController.class);

    @GetMapping
    public List<ChatDto> getAllChats() {
        log.info("Get all chats");
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public ChatDto getChatsById(@PathVariable Long id) {
        log.info("Get chat by id: {}", id);
        return chatService.getChatsById(id);
    }

    @PostMapping
    public ChatDto addChat(@RequestBody ChatDto chatDto) {
        log.info("Add chat: {}", chatDto);
        return chatService.addChat(chatDto);
    }

    @PutMapping("/{id}")
    public ChatDto updateChat(@PathVariable Long id, @RequestBody ChatDto chatDto) throws JsonProcessingException {
        log.info("Update chat with id: {}, chat: {}", id, new ObjectMapper().writeValueAsString(chatDto));
        return chatService.updateChat(id, chatDto);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Long id) {
        log.info("Delete chat with id: {}", id);
        chatService.deleteChat(id);
    }

    @GetMapping("/{chatId}/participants")
    public List<ChatParticipantDto> getAllChatParticipants(@PathVariable Long chatId) {
        log.info("Get all chat participants for chat with id: {}", chatId);
        return chatService.getAllChatParticipants(chatId);
    }

    @PostMapping("/{chatId}/participants")
    public List<ChatParticipantDto> addChatParticipants(@PathVariable Long chatId, @RequestBody ArrayList<Long> userIds) {
        log.info("Add chat participants for chat with id: {}, participants: {}", chatId, userIds);
        return chatService.addChatParticipants(chatId, userIds);
    }

    @DeleteMapping("/{chatId}/participants/{userId}")
    public void deleteChatParticipant(@PathVariable Long chatId, @PathVariable Long userId) {
        log.info("Delete chat participant with id: {} from chat with id: {}", userId, chatId);
        chatService.deleteChatParticipant(chatId, userId);
    }

}
