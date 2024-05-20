package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.ChatMessageDetailsResponse;

public interface ChatMessageService {
    ChatMessageDetailsResponse getMessageListByChatRoomId(String chatRoomId);
}
