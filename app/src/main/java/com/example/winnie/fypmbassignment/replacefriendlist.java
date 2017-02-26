package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 11/2/2017.
 */

public class replacefriendlist {

    public void replacefriendlist(){

        String string2 = "1A0,1A3,1A10,1A0A2,11A0";
        String searchForThis = "13";
        System.out.println("Search1            ="+string2.toUpperCase().contains(searchForThis.toUpperCase()));
        if(string2.toUpperCase().contains(searchForThis.toUpperCase())==true){
            string2 = string2.replaceAll("1A10"+",", "");
            System.out.println("Search2            ="+string2);
        }

    }
}
