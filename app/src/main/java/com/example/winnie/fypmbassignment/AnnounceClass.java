package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 18/2/2017.
 */

public class AnnounceClass {

    public final String course;
    public final String title;
    public final String DateD;
    public final String TimeD;
    public AnnounceClass(String course, String title,String TimeD, String DateD) {
        this.course  = course;
        this.title = title;
        this.DateD  = DateD;
        this.TimeD = TimeD;
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

    /**
     * Created by Winnie on 23/2/2017.
     */
}