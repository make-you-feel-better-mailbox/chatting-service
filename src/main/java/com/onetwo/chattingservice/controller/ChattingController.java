package com.onetwo.chattingservice.controller;

import com.onetwo.chattingservice.common.GlobalURI;
import com.onetwo.chattingservice.dto.ChatRoomListResponse;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import com.onetwo.chattingservice.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping(GlobalURI.CHATTING_ROOT)
    public ResponseEntity<RegisterChatRoomResponse> registerChatRoom(@RequestBody RegisterChatRoomRequest registerChatRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chattingService.registerChatRoom(registerChatRoomRequest));
    }

    @GetMapping(GlobalURI.CHATTING_ROOM + GlobalURI.PATH_VARIABLE_USER_ID_WITH_BRACE)
    public ResponseEntity<ChatRoomListResponse> getChatRoomListByUserId(@PathVariable(GlobalURI.PATH_VARIABLE_USER_ID) String userId) {
        return ResponseEntity.ok().body(chattingService.getChatRoomListByUserId(userId));
    }
}
