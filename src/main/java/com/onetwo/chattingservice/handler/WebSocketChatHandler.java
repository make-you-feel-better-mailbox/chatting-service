package com.onetwo.chattingservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onetwo.chattingservice.dto.ChatMessageDto;
import com.onetwo.chattingservice.exception.BadRequestException;
import com.onetwo.chattingservice.service.ChattingMessageService;
import com.onetwo.chattingservice.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final Map<String, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    private final ChattingRoomService chattingRoomService;

    private final ChattingMessageService chattingMessageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("WebSocket connect request - session id = {}", session.getId());

        String chatRoomId = getChatRoomId(session);

        if (!chatRoomSessionMap.containsKey(chatRoomId)) chatRoomSessionMap.put(chatRoomId, new HashSet<>());

        Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

        chatRoomSession.add(session);

        sessions.add(session);

        log.info("{} 연결됨", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatMessageDto chatMessageDto = mapper.readValue(payload, ChatMessageDto.class);
        log.info("session {}", chatMessageDto.toString());

        String chatRoomId = getChatRoomId(session);

        Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

        sendMessageToChatRoom(chatMessageDto, chatRoomSession, chatRoomId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
    }

    private String getChatRoomId(WebSocketSession session) {
        String chatRoomId;

        try {
            String requestURI = session.getUri().toString();

            String[] parts = requestURI.split("/");

            chatRoomId = chattingRoomService.findChatRoomByChatRoomId(parts[parts.length - 1])
                    .orElseThrow(() -> new BadRequestException("chat room id does not exist")).getChatRoomId();

            log.info("session id - {}, chat room id - {}", session.getId(), chatRoomId);
        } catch (Exception e) {
            log.info("WebSocket Connect Bad Request on get chat room id exception = " + e);
            throw new BadRequestException(e.getMessage());
        }

        return chatRoomId;
    }

    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.removeIf(sess -> !sessions.contains(sess));
    }

    private void sendMessageToChatRoom(ChatMessageDto chatMessageDto, Set<WebSocketSession> chatRoomSession, String chatRoomId) {
        chattingMessageService.registerChatMessage(chatMessageDto, chatRoomId);
        chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess, chatMessageDto));
    }


    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IllegalStateException e) {
            log.info(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}