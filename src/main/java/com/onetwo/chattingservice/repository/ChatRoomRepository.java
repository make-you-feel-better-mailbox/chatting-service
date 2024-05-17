package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    List<ChatRoom> findByChatUsersContainingAndState(String userId, Boolean state);
}
