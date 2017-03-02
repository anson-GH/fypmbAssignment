package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 25/2/2017.
 */

public class ChatClass {
    public final String Chatname;
    public final String ChatImage;
    public final String ChatMessage;
    public final String ChatTimeDate;
    public final String ChatKey;
    public final String ChatUser;


    public ChatClass(String Chatname, String ChatImage, String ChatTimeDate, String ChatMessage, String ChatKey, String ChatUser) {
        this.Chatname  = Chatname;
        this.ChatImage = ChatImage;
        this.ChatTimeDate  = ChatTimeDate;
        this.ChatMessage  = ChatMessage;
        this.ChatKey  = ChatKey;
        this.ChatUser  = ChatUser;

    }
    public String getChatname() {
        return Chatname;
    }

    public String getChatImage() {
        return ChatImage;
    }

    public String getChatTimeDate() {
        return ChatTimeDate;
    }
    public String getChatMessage() {
        return ChatMessage;
    }
    public String getChatUser() {
        return ChatUser;
    }
    public String getChatKey() {
        return ChatKey;
    }
}
