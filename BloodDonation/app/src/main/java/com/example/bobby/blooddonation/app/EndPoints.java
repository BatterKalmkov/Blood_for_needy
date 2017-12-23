package com.example.bobby.blooddonation.app;


public class EndPoints {

    // localhost url
    // public static final String BASE_URL = "http://192.168.0.101/gcm_chat/v1";

    public static final String BASE_URL = "http://sixthsenseit.com/blood/gcm_chat/v1";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String USER = BASE_URL + "/user/_ID_";
    public static final String CHAT_ROOMS = BASE_URL + "/chat_rooms";
    public static final String CHAT_THREAD = BASE_URL + "/chat_rooms/_ID_";
    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/chat_rooms/_ID_/message";

    public static final String LOGIN2 = BASE_URL + "/user/login2";
    public static final String USER2 = BASE_URL + "/user2/_ID_";
    public static final String USERS = BASE_URL + "/users";
    public static final String USER_THREAD = BASE_URL + "/users/_ID_";
    public static final String USER_ROOM_MESSAGE = BASE_URL + "/users/_ID_/message";

    public static final String BROADCAST_MESSAGE = BASE_URL + "/message";

}
