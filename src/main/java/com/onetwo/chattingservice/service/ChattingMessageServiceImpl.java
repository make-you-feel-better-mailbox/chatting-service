package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.ChatMessageDto;
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
    public void registerChatMessage(ChatMessageDto chatMessageDto, String chatRoomId) {
        ChatMessage chatMessage = ChatMessage
                .builder()
                .chatRoomId(chatRoomId)
                .message(chatMessageDto.getMessage())
                .senderId(chatMessageDto.getSenderId())
                .createdAt(Instant.now())
                .state(GlobalStatus.PERSISTENCE_NOT_DELETED)
                .build();

        chatMessageRepository.save(chatMessage);
    }
}
