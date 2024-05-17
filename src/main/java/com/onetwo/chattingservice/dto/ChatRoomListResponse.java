package com.onetwo.chattingservice.dto;

import java.util.List;

public record ChatRoomListResponse(List<ChatRoomDetailResponse> chatRoomDetailResponses) {
}
