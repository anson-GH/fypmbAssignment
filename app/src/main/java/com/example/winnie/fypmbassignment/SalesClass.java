package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 22/2/2017.
 */

public class SalesClass {
    public final String Name;
  //  public final String Price;
    public final String Image;



    public SalesClass(String Name, String Image) {
        this.Name  = Name;
        this.Image = Image;

    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }
}
