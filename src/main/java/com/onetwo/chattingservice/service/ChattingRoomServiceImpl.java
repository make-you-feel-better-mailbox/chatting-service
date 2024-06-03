package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.*;
import com.onetwo.chattingservice.entity.ChatMessage;
import com.onetwo.chattingservice.entity.ChatRoom;
import com.onetwo.chattingservice.grpc.UserGrpcClient;
import com.onetwo.chattingservice.repository.ChatMessageRepository;
import com.onetwo.chattingservice.repository.ChatRoomRepository;
import com.onetwo.rpc.user.UserInfo;
import lombok.RequiredArgsConstructor;
import onetwo.mailboxcommonconfig.common.exceptions.BadRequestException;
import onetwo.mailboxcommonconfig.common.exceptions.NotFoundResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChattingRoomServiceImpl implements ChattingRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserGrpcClient userGrpcClient;

    @Override
    @Transactional
    public RegisterChatRoomResponse registerChatRoom(RegisterChatRoomRequest registerChatRoomRequest) {
        if (registerChatRoomRequest.targetUserIds() == null || registerChatRoomRequest.targetUserIds().isEmpty())
            throw new BadRequestException("targetUserIds is should have value");

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
        List<ChatRoom> chatRoomList = chatRoomRepository.findByChatUsersContainingAndStateOrderByCreatedAtDesc(userId, GlobalStatus.PERSISTENCE_NOT_DELETED);

        List<ChatRoomDetailResponse> chatRoomDetailResponses = chatRoomList.stream()
                .map(e -> getChatRoomDetailResponse(e)).toList();

        return new ChatRoomListResponse(chatRoomDetailResponses);
    }

    private ChatRoomDetailResponse getChatRoomDetailResponse(ChatRoom chatRoom) {
        Random random = new Random();

        List<ChatUserDetail> chatUserDetails = getChatUserDetails(chatRoom);

        return new ChatRoomDetailResponse(
                chatRoom.getChatRoomId(),
                chatUserDetails,
                getLastChatDetail(chatRoom),
                random.nextBoolean(), //Todo 차후에 메시지 읽었는지 여부 확인하는 코드로 변경
                chatRoom.getCreatedAt());
    }

    private List<ChatUserDetail> getChatUserDetails(ChatRoom chatRoom) {
        return chatRoom.getChatUsers().stream().map(
                chatUserId -> {
                    UserInfo userInfo = userGrpcClient.getUserInfo(chatUserId);

                    return new ChatUserDetail(chatUserId, userInfo.getUserNickname(), userInfo.getProfileImageEndPoint());
                }
        ).toList();
    }

    private LastChatDetail getLastChatDetail(ChatRoom chatRoom) {
        Optional<ChatMessage> chatMessageOpt = chatMessageRepository.findFirstByChatRoomIdOrderByCreatedAtDesc(chatRoom.getChatRoomId());

        ChatMessage lastChat = chatMessageOpt.orElse(new ChatMessage());

        return new LastChatDetail(chatMessageOpt.isPresent(), lastChat.getMessage(), lastChat.getCreatedAt());
    }

    @Override
    public Optional<ChatRoom> findChatRoomByChatRoomId(String chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }

    @Override
    public ChatRoomExistResponse checkChatRoomExist(RegisterChatRoomRequest registerChatRoomRequest) {
        Optional<ChatRoom> chatRoomOpt =
                chatRoomRepository.findByChatUsersAndState(registerChatRoomRequest.targetUserIds(), GlobalStatus.PERSISTENCE_NOT_DELETED);

        ChatRoom chatRoom = chatRoomOpt.orElseGet(() -> new ChatRoom());

        return new ChatRoomExistResponse(chatRoomOpt.isPresent(), chatRoom.getChatRoomId());
    }

    @Override
    public ChatRoomDetailResponse getChatRoomDetail(String chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new NotFoundResourceException("chat room does not exist"));
        return getChatRoomDetailResponse(chatRoom);
    }
}
