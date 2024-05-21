package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.ChatRoomExistResponse;
import com.onetwo.chattingservice.dto.ChatRoomListResponse;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import com.onetwo.chattingservice.entity.ChatRoom;

import java.util.Optional;

public interface ChattingRoomService {
    RegisterChatRoomResponse registerChatRoom(RegisterChatRoomRequest registerChatRoomRequest);

    ChatRoomListResponse getChatRoomListByUserId(String userId);

    Optional<ChatRoom> findChatRoomByChatRoomId(String chatRoomId);

    ChatRoomExistResponse checkChatRoomExist(RegisterChatRoomRequest registerChatRoomRequest);
}
