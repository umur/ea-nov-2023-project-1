package com.project.alumni.chatservice.service;

import com.project.alumni.chatservice.dto.ChatMessagesDto;
import com.project.alumni.chatservice.entity.Chat;
import com.project.alumni.chatservice.entity.ChatMessage;
import com.project.alumni.chatservice.repository.ChatsMessagesRepository;
import com.project.alumni.chatservice.repository.ChatsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessagesServiceImpl implements ChatMessagesService {

    final ChatsRepository chatsRepository;
    final ChatsMessagesRepository chatMessagesRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ChatMessagesDto> getAllMessages(Long chatId) {
        Chat chat = findOrFail(chatId);
        List<ChatMessage> chatMessages = chat.getMessages();
        List<ChatMessagesDto> chatMessagesDtoList = new ArrayList<>();
        chatMessages.forEach(chatMessage -> chatMessagesDtoList.add(modelMapper.map(chatMessage, ChatMessagesDto.class)));
        return chatMessagesDtoList;
    }

    @Override
    public ChatMessagesDto getMessage(Long chatId, Long messageId) {
        ChatMessage chatMessage = chatMessagesRepository.findByIdAndDeletedAtIsNull(messageId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found with id: " + messageId)
        );

        return modelMapper.map(chatMessage, ChatMessagesDto.class);
    }

    @Override
    public ChatMessagesDto addMessage(Long chatId, ChatMessagesDto messageDto) {
        Chat chat = findOrFail(chatId);

        ChatMessage chatMessage = modelMapper.map(messageDto, ChatMessage.class);
        chatMessage.setId(null);
        chatMessage.setChat(chat);
        chatMessage.setCreatedAt(LocalDateTime.now());

        if (chat.getParticipants().stream().noneMatch(user -> user.getUserId().equals(chatMessage.getUserId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sender is not a participant of the chat");
        }

        chatMessagesRepository.save(chatMessage);
        return modelMapper.map(chatMessage, ChatMessagesDto.class);
    }

    @Override
    public ChatMessagesDto updateMessage(Long chatId, Long messageId, ChatMessagesDto messageDto) {

        Chat chat = findOrFail(chatId);

        Optional<ChatMessage> chatMessageOptional = chatMessagesRepository.findByIdAndDeletedAtIsNull(messageId);
        if (chatMessageOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found with id: " + messageId);
        }

        if (chat.getMessages().stream().noneMatch(message -> message.getId().equals(messageId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message is not in the chat");
        }

        ChatMessage chatMessage = chatMessageOptional.get();
        chatMessage.setContent(messageDto.getContent());
        chatMessage.setUpdatedAt(LocalDateTime.now());
        chatMessage.setId(messageId);
        ChatMessage savedChatMessage = chatMessagesRepository.save(chatMessage);

        return modelMapper.map(savedChatMessage, ChatMessagesDto.class);
    }

    @Override
    public void deleteMessage(Long chatId, Long messageId) {
        ChatMessage chatMessage = chatMessagesRepository.findByIdAndDeletedAtIsNull(messageId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found with id: " + messageId)
        );

        chatMessage.setDeletedAt(LocalDateTime.now());
        chatMessagesRepository.save(chatMessage);
    }

    Chat findOrFail(Long id) throws ResponseStatusException {
        return chatsRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "chat not found with id: " + id)
        );
    }
}
