package com.onetwo.chattingservice.controller;

import com.onetwo.chattingservice.common.GlobalURI;
import com.onetwo.chattingservice.dto.ChatRoomExistResponse;
import com.onetwo.chattingservice.dto.ChatRoomListResponse;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import com.onetwo.chattingservice.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping(GlobalURI.CHATTING_ROOT)
    public ResponseEntity<RegisterChatRoomResponse> registerChatRoom(@RequestBody RegisterChatRoomRequest registerChatRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chattingRoomService.registerChatRoom(registerChatRoomRequest));
    }

    @GetMapping(GlobalURI.CHATTING_ROOM)
    public ResponseEntity<ChatRoomListResponse> getChatRoomListByUserId(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok().body(chattingRoomService.getChatRoomListByUserId(userId));
    }

    @GetMapping(GlobalURI.CHATTING_ROOT)
    public ResponseEntity<ChatRoomExistResponse> checkChatRoomExist(@ModelAttribute RegisterChatRoomRequest registerChatRoomRequest) {
        return ResponseEntity.ok().body(chattingRoomService.checkChatRoomExist(registerChatRoomRequest));
    }
}
