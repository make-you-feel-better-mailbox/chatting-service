package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.common.GlobalStatus;
import com.onetwo.chattingservice.dto.ChatMessageDetail;
import com.onetwo.chattingservice.dto.ChatMessageDetailsResponse;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserGrpcClient userGrpcClient;

    @Override
    public ChatMessageDetailsResponse getMessageListByChatRoomId(String chatRoomId, String userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new NotFoundResourceException("chat room does not exist"));

        if (!chatRoom.getChatUsers().contains(userId))
            throw new BadRequestException("Only chat members can get chat messages");

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatRoomIdAndStateOrderByCreatedAtDesc(
                chatRoom.getChatRoomId(),
                GlobalStatus.PERSISTENCE_NOT_DELETED
        );

        List<ChatMessageDetail> chatMessageDetails = chatMessageList.stream()
                .map(e -> {
                            UserInfo userInfo = userGrpcClient.getUserInfo(e.getSenderId());
                            return new ChatMessageDetail(e.getSenderId(), e.getMessage(), userInfo.getProfileImageEndPoint(), e.getCreatedAt());
                        }
                ).toList();

        return new ChatMessageDetailsResponse(chatMessageDetails);
    }
}
