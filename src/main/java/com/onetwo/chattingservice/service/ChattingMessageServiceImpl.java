package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.ChatMessageRequestDto;
import com.onetwo.chattingservice.entity.ChatMessage;
import com.onetwo.chattingservice.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ChattingMessageServiceImpl implements ChattingMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public void registerChatMessage(ChatMessageRequestDto chatMessageRequestDto, String chatRoomId) {
        ChatMessage chatMessage = ChatMessage
                .builder()
                .chatRoomId(chatRoomId)
                .message(chatMessageRequestDto.getMessage())
                .senderId(chatMessageRequestDto.getSenderId())
                .createdAt(Instant.now())
                .state(GlobalStatus.PERSISTENCE_NOT_DELETED)
                .build();

        chatMessageRepository.save(chatMessage);
    }
}
