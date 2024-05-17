package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
