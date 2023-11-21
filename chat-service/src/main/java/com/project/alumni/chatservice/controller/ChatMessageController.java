package com.project.alumni.chatservice.controller;

import com.project.alumni.chatservice.dto.ChatMessagesDto;
import com.project.alumni.chatservice.service.ChatMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chats/{chatId}/messages")
public class ChatMessageController {

    private final ChatMessagesService chatMessagesService;

    @GetMapping
    public List<ChatMessagesDto> getAllMessages(@PathVariable Long chatId) {
        return chatMessagesService.getAllMessages(chatId);
    }

    @GetMapping("/{messageId}")
    public ChatMessagesDto getMessage(@PathVariable Long chatId, @PathVariable Long messageId) {
        return chatMessagesService.getMessage(chatId, messageId);
    }

    @PostMapping
    public ChatMessagesDto addMessage(@PathVariable Long chatId, @RequestBody ChatMessagesDto messageDto) {
        return chatMessagesService.addMessage(chatId, messageDto);
    }

    @PutMapping("/{messageId}")
    public ChatMessagesDto updateMessage(@PathVariable Long chatId, @PathVariable Long messageId, @RequestBody ChatMessagesDto messageDto) {
        return chatMessagesService.updateMessage(chatId, messageId, messageDto);
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable Long chatId, @PathVariable Long messageId) {
        chatMessagesService.deleteMessage(chatId, messageId);
    }

}
