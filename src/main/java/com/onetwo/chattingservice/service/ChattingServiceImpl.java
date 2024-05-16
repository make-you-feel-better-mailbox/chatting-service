package com.onetwo.chattingservice.service;

import com.onetwo.chattingservice.dto.RegisterChatRoomRequest;
import com.onetwo.chattingservice.dto.RegisterChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService {

    
    @Override
    public RegisterChatRoomResponse registerChatRoom(RegisterChatRoomRequest registerChatRoomRequest) {
        return null;
    }
}
