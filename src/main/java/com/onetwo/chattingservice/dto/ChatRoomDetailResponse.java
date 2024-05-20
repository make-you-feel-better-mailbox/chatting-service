package com.onetwo.chattingservice.dto;

import java.time.Instant;
import java.util.List;

public record ChatRoomDetailResponse(
        String chatRoomId,
        List<ChatUserDetail> chatUsers,
        Boolean unreadMessageExist,
        Instant createdAt
) {
}
