package com.onetwo.chattingservice.repository;

import com.onetwo.chattingservice.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    List<ChatRoom> findByChatUsersContainingAndStateOrderByCreatedAtDesc(String userId, Boolean state);

    Optional<ChatRoom> findByChatUsersAndState(List<String> userIds, Boolean state);
}
