package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
}
