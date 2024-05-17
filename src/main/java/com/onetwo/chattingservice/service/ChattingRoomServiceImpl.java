package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.ChatRoomDetailResponse;
import com.onetwo.chattingservice.dto.ChatRoomListResponse;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import com.onetwo.chattingservice.entity.ChatRoom;
import com.onetwo.chattingservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChattingRoomServiceImpl implements ChattingRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public RegisterChatRoomResponse registerChatRoom(RegisterChatRoomRequest registerChatRoomRequest) {
        List<String> chatRoomUserIds = registerChatRoomRequest.targetUserIds();

        ChatRoom chatRoom = ChatRoom
                .builder()
                .chatUsers(chatRoomUserIds)
                .state(GlobalStatus.PERSISTENCE_NOT_DELETED)
                .createdAt(Instant.now())
                .build();

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        return new RegisterChatRoomResponse(savedChatRoom.getChatRoomId());
    }

    @Override
    @Transactional(readOnly = true)
    public ChatRoomListResponse getChatRoomListByUserId(String userId) {
        List<ChatRoom> chatRoomList = chatRoomRepository.findByChatUsersContainingAndState(userId, GlobalStatus.PERSISTENCE_NOT_DELETED);

        List<ChatRoomDetailResponse> chatRoomDetailResponses = chatRoomList.stream()
                .map(e -> new ChatRoomDetailResponse(
                        e.getChatRoomId(),
                        e.getChatUsers(),
                        e.getCreatedAt())
                ).toList();

        return new ChatRoomListResponse(chatRoomDetailResponses);
    }

    @Override
    public Optional<ChatRoom> findChatRoomByChatRoomId(String chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }
}
