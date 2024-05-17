package com.onetwo.chattingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
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
