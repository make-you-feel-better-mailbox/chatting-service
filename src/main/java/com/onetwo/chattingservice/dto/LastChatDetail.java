package com.onetwo.chattingservice.dto;

import java.time.Instant;

public record LastChatDetail(Boolean chatExist, String lastChatMessage, Instant lastChatDate) {
}
