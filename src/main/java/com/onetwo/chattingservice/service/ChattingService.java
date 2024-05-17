package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.ChatRoomListResponse;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;

public interface ChattingService {
    RegisterChatRoomResponse registerChatRoom(RegisterChatRoomRequest registerChatRoomRequest);

    ChatRoomListResponse getChatRoomListByUserId(String userId);
}
