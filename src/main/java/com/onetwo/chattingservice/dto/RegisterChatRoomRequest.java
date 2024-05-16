package com.onetwo.chattingservice.dto;

import java.util.List;

public record RegisterChatRoomRequest(
        List<Long> targetUserIds
) {
}
