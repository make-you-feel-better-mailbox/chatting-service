package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    Optional<ChatMessage> findFirstByChatRoomIdOrderByCreatedAtDesc(String chatRoomId);
}
