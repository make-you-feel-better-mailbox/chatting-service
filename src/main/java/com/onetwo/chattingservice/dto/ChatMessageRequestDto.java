package com.onetwo.chattingservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequestDto {

    private String senderId;
    private String message;
}
