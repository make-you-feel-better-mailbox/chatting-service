package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    @Query(value = "{ 'chatRoomId' : ?0 }", sort = "{ 'createdAt' : -1 }")
    Optional<ChatMessage> findTopByChatRoomIdOrderByCreatedAtDesc(String chatRoomId);
}
