package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 5/2/2017.
 */

public class User {

    String firebaseid, username, password, email;



    public User(String username, String password, String email,String firebaseid) {
        this.username = username;
        this.password = password;
        this.firebaseid = firebaseid;
        this.email = email;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firebaseid = "";
        this.email = "";
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;

    }
}
