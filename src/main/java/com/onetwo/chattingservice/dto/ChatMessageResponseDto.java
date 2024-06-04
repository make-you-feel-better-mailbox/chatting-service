package com.onetwo.chattingservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponseDto {

    private String senderId;
    private String userProfileImageEndPoint;
    private String message;
}
