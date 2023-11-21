package com.project.alumni.chatservice.service;

import com.project.alumni.chatservice.dto.ChatDto;
import com.project.alumni.chatservice.dto.ChatParticipantDto;
import com.project.alumni.chatservice.entity.Chat;
import com.project.alumni.chatservice.entity.ChatParticipant;
import com.project.alumni.chatservice.entity.ExternalUserDto;
import com.project.alumni.chatservice.repository.ChatsRepository;
import com.project.alumni.chatservice.service.external.UsersClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    final ChatsRepository chatsRepository;
    final UsersClient usersClient;
    final ModelMapper modelMapper;


    @Override
    public List<ChatDto> getAllChats() {
        List<ChatDto> chatDtoList = new ArrayList<>();
        chatsRepository.findAllByDeletedAtIsNull().forEach(chat -> {
            chatDtoList.add(modelMapper.map(chat, ChatDto.class));
        });
        return chatDtoList;
    }

    @Override
    public ChatDto getChatsById(Long id) {
        Chat chat = findOrFail(id);
        return modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public ChatDto addChat(ChatDto chatDto) {
        if (chatDto.getIsGroup()) {
            if (chatDto.getName() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name is required");
            }
        } else {
            chatDto.setName("One to One");
        }
        Chat chat = modelMapper.map(chatDto, Chat.class);
        chat.setId(null);
        chat.setCreatedAt(LocalDateTime.now());
        Chat savedChat = chatsRepository.save(chat);
        return modelMapper.map(savedChat, ChatDto.class);
    }

    @Override
    public ChatDto updateChat(Long id, ChatDto chatDto) {
        Chat chat = findOrFail(id);

        if (chat.getIsGroup()) {
            if (chat.getParticipants().size() > 2) {
                if (false == chatDto.getIsGroup()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change group chat to one to one chat if there are more than 2 participants");
                }
            }
        }

        modelMapper.map(chatDto, chat);
        chat.setId(id);
        chat.setUpdatedAt(LocalDateTime.now());
        Chat savedChat = chatsRepository.save(chat);
        return modelMapper.map(savedChat, ChatDto.class);

    }

    @Override
    public void deleteChat(Long id) {
        Chat chat = findOrFail(id);
        chat.setDeletedAt(LocalDateTime.now());
        chatsRepository.save(chat);
    }

    @Override
    public List<ChatParticipantDto> getAllChatParticipants(Long chatId) {
        Chat chat = findOrFail(chatId);
        List<ChatParticipantDto> ChatParticipantDtoList = new ArrayList<>();
        chat.getParticipants().forEach(participant -> {
            ChatParticipantDtoList.add(modelMapper.map(participant, ChatParticipantDto.class));
        });

        return ChatParticipantDtoList;
    }


    @Override
    public List<ChatParticipantDto> addChatParticipants(Long chatId, List<Long> userIds) {
        Chat chat = findOrFail(chatId);
        chat.setUpdatedAt(LocalDateTime.now());
        List<ChatParticipant> chatParticipants = chat.getParticipants();

        List<ExternalUserDto> usersToAdd = usersClient.findAllByIdIn(userIds);

        List<Long> usersNotFound = userIds.stream().filter(userId -> usersToAdd.stream().noneMatch(user -> user.getId().equals(userId))).toList();
        if (!usersNotFound.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users with ids: " + usersNotFound.toString() + " not found");
        }

        List<Long> usersAlreadyInChat = usersToAdd.stream().filter(chatParticipants::contains).map(ExternalUserDto::getId).toList();
        if (!usersAlreadyInChat.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users with ids: " + usersAlreadyInChat.toString() + " are already in the chat");
        }

        List<ChatParticipant> chatParticipantsToAdd = usersToAdd.stream().map(user -> {
            ChatParticipant chatParticipant = new ChatParticipant();
            chatParticipant.setChat(chat);
            chatParticipant.setUserId(user.getId());
            return chatParticipant;
        }).toList();


        chatParticipants.addAll(chatParticipantsToAdd);
        chat.setParticipants(chatParticipants);
        chatsRepository.save(chat);
        return usersToAdd.stream().map(user -> modelMapper.map(user, ChatParticipantDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteChatParticipant(Long chatId, Long userId) {
        Chat chat = findOrFail(chatId);
        chat.setUpdatedAt(LocalDateTime.now());

        if (false == chat.getParticipants().stream().anyMatch(participant -> participant.getUserId().equals(userId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not participant of this chat");
        }

        chat.getParticipants().removeIf(participant -> participant.getUserId().equals(userId));
        chatsRepository.save(chat);
    }

    Chat findOrFail(Long id) throws ResponseStatusException {
        return chatsRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "chat not found with id: " + id)
        );
    }
}
