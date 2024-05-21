package com.onetwo.chattingservice.common;

public class GlobalURI {

    public static final String ROOT_URI = "/chatting-service";

    public static final String UNDER_ROUTE = "/*";
    public static final String EVERY_UNDER_ROUTE = "/**";

    public static final String MESSAGE_ROOT = ROOT_URI + "/message";
    public static final String CHATTING_ROOT = ROOT_URI + "/chatting";
    public static final String CHATTING_ROOM = CHATTING_ROOT + "/rooms";
    public static final String CHATTING_ROOM_DETAIL = CHATTING_ROOM + "/details";
    public static final String CHATTING_MESSAGE = CHATTING_ROOT + "/messages";

    public static final String PATH_VARIABLE_USER_ID = "user-id";
    public static final String PATH_VARIABLE_USER_ID_WITH_BRACE = "/{" + PATH_VARIABLE_USER_ID + "}";

    public static final String PATH_VARIABLE_CHAT_ROOM_ID = "chat-room-id";
    public static final String PATH_VARIABLE_CHAT_ROOM_ID_WITH_BRACE = "/{" + PATH_VARIABLE_CHAT_ROOM_ID + "}";
}
