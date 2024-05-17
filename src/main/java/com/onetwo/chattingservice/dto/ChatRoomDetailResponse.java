package com.onetwo.chattingservice.dto;

import java.time.Instant;
import java.util.List;

public record ChatRoomDetailResponse(
        String chatRoomId,
        List<String> chatUsers,
        Instant createdAt
) {
}
