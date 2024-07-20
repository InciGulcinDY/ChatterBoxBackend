package com.tobeto.ChatterBoxBackend.repositories;

import com.tobeto.ChatterBoxBackend.entities.concretes.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findChatMessageByRoom(String room);

}
