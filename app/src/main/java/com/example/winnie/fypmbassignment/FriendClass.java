package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 18/2/2017.
 */

public class FriendClass {

    public final String Username;
    public final String Userposition;
    public final String UserID;
    public final String studentImage;

    public FriendClass(String Username, String Userposition, String UserID, String studentImage) {
        this.Username  = Username;
        this.Userposition = Userposition;
        this.studentImage  = studentImage;
        this.UserID  = UserID;

    }

    public String getUsername() {
        return Username;
    }

    public String getUserposition() {
        return Userposition;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public String getUserID() {
        return UserID;
    }
}