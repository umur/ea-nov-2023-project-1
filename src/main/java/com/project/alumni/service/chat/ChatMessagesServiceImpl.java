package com.project.alumni.service.chat;

import com.project.alumni.dto.chat.ChatMessagesDto;
import com.project.alumni.entity.chat.Chat;
import com.project.alumni.entity.chat.ChatMessage;
import com.project.alumni.repository.chat.ChatsMessagesRepository;
import com.project.alumni.repository.chat.ChatsRepository;
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

        if (chat.getParticipants().stream().noneMatch(user -> user.getId().equals(chatMessage.getSender().getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sender is not a participant of the chat");
        }

        List<ChatMessage> chatMessages = chat.getMessages();
        chatMessages.add(chatMessage);
        chat.setMessages(chatMessages);
        Chat savedChat = chatsRepository.save(chat);
        return modelMapper.map(savedChat, ChatMessagesDto.class);
    }

    @Override
    public ChatMessagesDto updateMessage(Long chatId, Long messageId, ChatMessagesDto messageDto) {
        Optional<ChatMessage> chatMessageOptional = chatMessagesRepository.findByIdAndDeletedAtIsNull(messageId);
        if (chatMessageOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message not found with id: " + messageId);
        }

        ChatMessage chatMessage = modelMapper.map(messageDto, ChatMessage.class);
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
