package com.project.alumni.repository.chat;

import com.project.alumni.entity.chat.Chat;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatsRepository extends ListCrudRepository<Chat, Long> {

    List<Chat> findAllByDeletedAtIsNull();

    Optional<Chat> findByIdAndDeletedAtIsNull(Long id);

}
