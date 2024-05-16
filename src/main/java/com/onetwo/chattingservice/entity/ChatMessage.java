package com.onetwo.chattingservice.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@Document(collection = "chat_message")
public class ChatMessage {

    @Id
    private String chatId;
    private String chatRoomId;
    private String senderId;
    private String message;
    private Boolean state;
    private Instant createdAt;
}
