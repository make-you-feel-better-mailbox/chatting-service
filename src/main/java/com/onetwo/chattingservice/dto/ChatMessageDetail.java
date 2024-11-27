package com.onetwo.chattingservice.dto;

import java.time.Instant;

public record ChatMessageDetail(String senderId, String message, String userProfileEndPoint, Instant createdAt) {
}
