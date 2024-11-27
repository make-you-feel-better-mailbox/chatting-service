package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.ChatMessageRequestDto;

public interface ChattingMessageService {
    void registerChatMessage(ChatMessageRequestDto chatMessageRequestDto, String chatRoomId);
}
