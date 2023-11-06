package com.project.alumni.service.chat;

import com.project.alumni.dto.chat.ChatDto;
import com.project.alumni.dto.UserLoginDto;
import com.project.alumni.entity.chat.Chat;
import com.project.alumni.entity.User;
import com.project.alumni.repository.chat.ChatsRepository;
import com.project.alumni.repository.UserRepository;
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
    final UserRepository userRepository;
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
    public List<UserLoginDto> getAllChatParticipants(Long chatId) {
        Chat chat = findOrFail(chatId);
        List<UserLoginDto> UserLoginDtoList = new ArrayList<>();
        chat.getParticipants().forEach(participant -> {
            UserLoginDtoList.add(modelMapper.map(participant, UserLoginDto.class));
        });

        return UserLoginDtoList;
    }


    @Override
    public List<UserLoginDto> addChatParticipants(Long chatId, List<Long> userIds) {
        Chat chat = findOrFail(chatId);
        chat.setUpdatedAt(LocalDateTime.now());
        List<User> chatParticipants = chat.getParticipants();

        List<User> usersToAdd = userRepository.findAllByIdIn(userIds);

        List<Long> usersNotFound = userIds.stream().filter(userId -> usersToAdd.stream().noneMatch(user -> user.getId().equals(userId))).toList();
        if (!usersNotFound.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users with ids: " + usersNotFound.toString() + " not found");
        }

        List<Long> usersAlreadyInChat = usersToAdd.stream().filter(chatParticipants::contains).map(User::getId).toList();
        if (!usersAlreadyInChat.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users with ids: " + usersAlreadyInChat.toString() + " are already in the chat");
        }

        chatParticipants.addAll(usersToAdd);
        chat.setParticipants(chatParticipants);
        chatsRepository.save(chat);
        return usersToAdd.stream().map(user -> modelMapper.map(user, UserLoginDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteChatParticipant(Long chatId, Long userId) {
        Chat chat = findOrFail(chatId);
        chat.setUpdatedAt(LocalDateTime.now());

        if (false == chat.getParticipants().stream().anyMatch(participant -> participant.getId().equals(userId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not participant of this chat");
        }

        chat.getParticipants().removeIf(participant -> participant.getId().equals(userId));
        chatsRepository.save(chat);
    }

    Chat findOrFail(Long id) throws ResponseStatusException {
        return chatsRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "chat not found with id: " + id)
        );
    }
}
