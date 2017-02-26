package com.example.winnie.fypmbassignment.chat;

/**
 * Created by MSI on 2017-02-10.
 */
public class Message {
    private String email;
    private String message;
    private String time;

    public Message( String email, String message, String time) {
        this.email = email;
        this.message = message;
        this.time = time;
    }
    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }


}