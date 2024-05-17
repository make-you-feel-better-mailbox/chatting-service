package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.ChatMessageDto;

public interface ChattingMessageService {
    void registerChatMessage(ChatMessageDto chatMessageDto, String chatRoomId);
}
