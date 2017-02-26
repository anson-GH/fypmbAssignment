package com.example.winnie.fypmbassignment;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Winnie on 5/2/2017.
 */


public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("email", user.email);
        userLocalDatabaseEditor.putString("username", user.username);
        userLocalDatabaseEditor.putString("password", user.password);
        userLocalDatabaseEditor.putString("firebaseid", user.firebaseid);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String email = userLocalDatabase.getString("email", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String firebaseid = userLocalDatabase.getString("firebaseid", "");

        User user = new User( username, password,email, firebaseid);
        return user;
    }
}

//
//public class UserLocalStore {
//
//    public static final String SP_NAME = "userDetails";
//
//    SharedPreferences userLocalDatabase;
//
//    public UserLocalStore(Context context) {
//        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
//    }
//
//    public void storeUserData(User user) {
//        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
//        userLocalDatabaseEditor.putString("username", user.username);
//        userLocalDatabaseEditor.putString("password", user.password);
//        userLocalDatabaseEditor.putString("email", user.email);
//        userLocalDatabaseEditor.putString("firebaseid", user.firebaseid);
//        userLocalDatabaseEditor.commit();
//
//System.out.println("aaaaaaaaaaaaaaa   " +  user.username +" h " +  user.password +" h " + user.email +" h " + user.firebaseid   );
//    }
//
//    public void setUserLoggedIn(boolean loggedIn) {
//        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
//        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
//        userLocalDatabaseEditor.commit();
//    }
//
//    public void clearUserData() {
//        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
//        userLocalDatabaseEditor.clear();
//        userLocalDatabaseEditor.commit();
//    }
//
//    public User getLoggedInUser() {
//        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
//            return null;
//        }
//
//        String username = userLocalDatabase.getString("username", "");
//        String password = userLocalDatabase.getString("password", "");
//        String email = userLocalDatabase.getString("email", "");
//        String firebaseid = userLocalDatabase.getString("firebaseid", "");
//
//        System.out.println("aaaaaaaaaaaaaaa   " +  username +" h " +  password +" h " + email +" h " + firebaseid   );
//
//        User user = new User(username, password, email, firebaseid);
//        return user;
//    }
//}

