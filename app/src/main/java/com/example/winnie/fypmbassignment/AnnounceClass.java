package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 18/2/2017.
 */

public class AnnounceClass {

    public final String course;
    public final String title;
    public final String DateD;
    public final String TimeD;
    public final String KeyDB;
    public final String Messages;
    public final String LecturerD;
    public final String classGroup;



    public AnnounceClass(String course, String title, String TimeD, String DateD, String KeyDB, String Messages, String LecturerD, String classGroup) {
        this.course  = course;
        this.title = title;
        this.DateD  = DateD;
        this.TimeD = TimeD;
        this.KeyDB  = KeyDB;
        this.Messages = Messages;
        this.LecturerD  = LecturerD;
        this.classGroup = classGroup;
    }

    public String getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public String getDateD() {
        return DateD;
    }

    public String getTimeD() {
        return TimeD;
    }

    public String getKeyDB() {
        return KeyDB;
    }

    public String getMessages() {
        return Messages;
    }

    public String getLecturerD() {
        return LecturerD;
    }

    public String getClassGroup() {
        return classGroup;
    }
}