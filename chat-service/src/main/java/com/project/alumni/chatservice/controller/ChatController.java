package com.project.alumni.chatservice.controller;

import com.project.alumni.chatservice.dto.ChatDto;
import com.project.alumni.chatservice.dto.ChatParticipantDto;
import com.project.alumni.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatDto> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public ChatDto getChatsById(@PathVariable Long id) {
        return chatService.getChatsById(id);
    }

    @PostMapping
    public ChatDto addChat(@RequestBody ChatDto chatDto) {
        return chatService.addChat(chatDto);
    }

    @PutMapping("/{id}")
    public ChatDto updateChat(@PathVariable Long id, @RequestBody ChatDto chatDto) {
        return chatService.updateChat(id, chatDto);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
    }

    @GetMapping("/{chatId}/participants")
    public List<ChatParticipantDto> getAllChatParticipants(@PathVariable Long chatId) {
        return chatService.getAllChatParticipants(chatId);
    }

    @PostMapping("/{chatId}/participants")
    public List<ChatParticipantDto> addChatParticipants(@PathVariable Long chatId, @RequestBody ArrayList<Long> userIds) {
        return chatService.addChatParticipants(chatId, userIds);
    }

    @DeleteMapping("/{chatId}/participants/{userId}")
    public void deleteChatParticipant(@PathVariable Long chatId, @PathVariable Long userId) {
        chatService.deleteChatParticipant(chatId, userId);
    }

}
