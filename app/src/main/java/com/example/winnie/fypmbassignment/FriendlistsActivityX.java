package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.winnie.fypmbassignment.chat.Chat_Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FriendlistsActivityX extends AppCompatActivity {
    FloatingActionButton buttonplus,buttonAddfren;
    Animation fab_scale_down,fab_scale_up,fab_Clockwise,fab_Anticlockwise;
    boolean isOpen = false;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().getRoot().child("chat");
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    ArrayList<String> friendAll = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlists);

        buttonplus = (FloatingActionButton) findViewById(R.id.buttonplus);
        buttonAddfren = (FloatingActionButton) findViewById(R.id.buttonAddfren);

        fab_scale_up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_scale_up);
        fab_scale_down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_scale_down);
        fab_Clockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fab_Anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    buttonplus.startAnimation(fab_Anticlockwise);
                    buttonAddfren.startAnimation(fab_scale_down);
                    buttonAddfren.setClickable(false);
                    isOpen = false;
                }else
                {
                    buttonplus.startAnimation(fab_Clockwise);
                    buttonAddfren.startAnimation(fab_scale_up);
                    buttonAddfren.setClickable(true);
                isOpen = true;
                }
            }
        });
        buttonAddfren.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendlistsActivityX.this, FriendSearchlistActivity.class);
                startActivity(intent);
            }
        });


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_rooms);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().getRoot().child("student").child("12AAD1212").child("friendlist");
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                while (it.iterator().hasNext()) {
                    DataSnapshot ds = it.iterator().next();
                    System.out.println("friendlist     "+ds.getKey().toString());
                    friendAll.add(ds.getKey().toString());
                }


                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Set<String> set = new HashSet<String>();
                        // Iterator it = dataSnapshot.getChildren();
                        Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                        System.out.println("asdadasdasdasdasdasd");
                        while (it.iterator().hasNext()) {
                            DataSnapshot ds = it.iterator().next();

                            for(int j =0;j< friendAll.size();j++) {

                                if(friendAll.get(j).contains(ds.getKey().toString())) {
                                    System.out.println("friendlists     " + ds.getKey().toString());

                                    set.add(ds.getKey().toString());
                                }
                            }
                        }

                        list_of_rooms.clear();
                        list_of_rooms.addAll(set);

                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), Chat_Room.class);
                intent.putExtra("room_Key", ((TextView) view).getText().toString());
                intent.putExtra("friend_name", ((TextView) view).getText().toString());
                intent.putExtra("user_name", "John");
                startActivity(intent);
            }
        });




    }
}
