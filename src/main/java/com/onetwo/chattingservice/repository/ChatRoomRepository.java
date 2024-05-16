package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
}
