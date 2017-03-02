package com.example.winnie.fypmbassignment;

/**
 * Created by Winnie on 22/2/2017.
 */

public class SalesClass {
    public final String Name;
    public final String idKey;
    public final String Price;
    public final String Description;
    public final String Condition;
    public final String Category;
    public final String Location;
    public final String Image1;
    public final String Image2;
    public final String Image3;
    public final String  Timestamp;
    public final String Status;
    public final String StudID;





    public SalesClass(String idKey, String Name, String Price, String Description, String Condition, String Category, String Location, String Image1,
                      String Image2, String Image3, String Timestamp, String Status, String StudID) {
        this.idKey = idKey;
        this.Name  = Name;
        this.Price  = Price;
        this.Description = Description;
        this.Condition = Condition;
        this.Category  = Category;
        this.Location = Location;
        this.Image1 = Image1;
        this.Image2 = Image2;
        this.Image3 = Image3;
        this.Timestamp = Timestamp;

        this.Status = Status;
        this.StudID = StudID;
    }

    public String getName() {
        return Name;
    }

    public String getIdKey() {
        return idKey;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getCondition() {
        return Condition;
    }

    public String getCategory() {
        return Category;
    }

    public String getLocation() {
        return Location;
    }

    public String getImage1() {
        return Image1;
    }

    public String getImage2() {
        return Image2;
    }

    public String getImage3() {
        return Image3;
    }
    public String getTimestamp() {
        return Timestamp;
    }

    public String getStatus() {
        return Status;
    }

    public String getStudID() {
        return StudID;
    }
}
