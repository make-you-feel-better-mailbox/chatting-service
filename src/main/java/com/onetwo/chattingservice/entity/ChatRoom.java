package com.onetwo.chattingservice.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Document(collection = "chat_room")
public class ChatRoom {

    @Id
    private String chatRoomId;
    private List<String> chatUserList;
    private Boolean state;
    private Instant createdAt;
}
