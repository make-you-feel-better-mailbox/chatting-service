package com.onetwo.chattingservice.controller;

import com.onetwo.chattingservice.common.GlobalURI;
import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import com.onetwo.chattingservice.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping(GlobalURI.CHATTING_ROOT)
    public ResponseEntity<RegisterChatRoomResponse> registerChatRoom(@RequestBody RegisterChatRoomRequest registerChatRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chattingService.registerChatRoom(registerChatRoomRequest));
    }
}
