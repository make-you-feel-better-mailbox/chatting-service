package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.ChatMessageDetail;
import com.onetwo.chattingservice.dto.ChatMessageDetailsResponse;
import com.onetwo.chattingservice.entity.ChatMessage;
import com.onetwo.chattingservice.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessageDetailsResponse getMessageListByChatRoomId(String chatRoomId) {
        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatRoomIdAndStateOrderByCreatedAtDesc(
                chatRoomId,
                GlobalStatus.PERSISTENCE_NOT_DELETED
        );

        List<ChatMessageDetail> chatMessageDetails = chatMessageList.stream()
                .map(e -> new ChatMessageDetail(e.getSenderId(), e.getMessage(), e.getCreatedAt())
                ).toList();

        return new ChatMessageDetailsResponse(chatMessageDetails);
    }
}
