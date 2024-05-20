package com.onetwo.chattingservice.controller;

import com.onetwo.chattingservice.common.GlobalURI;
import com.onetwo.chattingservice.dto.ChatMessageDetailsResponse;
import com.onetwo.chattingservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChattingMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping(GlobalURI.CHATTING_MESSAGE + GlobalURI.PATH_VARIABLE_CHAT_ROOM_ID_WITH_BRACE)
    public ResponseEntity<ChatMessageDetailsResponse> getMessageListByChatRoomId(
            @PathVariable(GlobalURI.PATH_VARIABLE_CHAT_ROOM_ID) String chatRoomId) {
        return ResponseEntity.ok().body(chatMessageService.getMessageListByChatRoomId(chatRoomId));
    }
}
