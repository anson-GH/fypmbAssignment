package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.winnie.fypmbassignment.Profile.ProfileViewActivity;

public class MainMenuActivity extends ActionBarActivity {
    GridView grid;
    String[] web = {
            "announcement",
            "profile",
            "online store",
            "live chat"

    } ;
    int[] imageId = {
            R.drawable.announcem,
            R.drawable.ic_people,
            R.drawable.shop,
            R.drawable.ic_chattss
    };
    Intent intent;
    ImageView imageView;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        userLocalStore = new UserLocalStore(this);

        if (authenticate() == true) {
            imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageResource(R.drawable.tarclogo);
            CustomGrid adapter = new CustomGrid(MainMenuActivity.this, web, imageId);
            grid = (GridView) findViewById(R.id.grid);
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //Toast.makeText(MainMenuActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                    if (position == 1) {
                        intent = new Intent(MainMenuActivity.this, ProfileViewActivity.class);
                        startActivity(intent);
                    }
                }
            });

    }

    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
          finish();

    }
}