package com.example.winnie.fypmbassignment.chat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.winnie.fypmbassignment.FriendChatlistActivity;
import com.example.winnie.fypmbassignment.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Chat_Room  extends AppCompatActivity {

    private Button btn_send_msg;
    private EditText input_msg;


    private DatabaseReference root;
    private String temp_key;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private String user_name,room_Key,friend_name;
    private ArrayList<Message> messages;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2C3B4F"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        user_name = getIntent().getExtras().get("user_name").toString();
        room_Key = getIntent().getExtras().get("room_Key").toString();
        friend_name = getIntent().getExtras().get("friend_name").toString();
            setTitle(friend_name);


        btn_send_msg = (Button) findViewById(R.id.btn_send);
        input_msg = (EditText) findViewById(R.id.msg_input);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        messages = new ArrayList<>();
        root = FirebaseDatabase.getInstance().getReference().child("chat").child(room_Key);
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btn_send_msg.setEnabled(false);
        input_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(s.toString().length()>0){
                    btn_send_msg.setEnabled(true);
                } else {
                    btn_send_msg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date d = new Date();
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(temp_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", user_name);
                map2.put("msg", input_msg.getText().toString());
                map2.put("time", DateFormat.format("yyyy-MM-dd hh:mm:ss A", d.getTime()));

                message_root.updateChildren(map2);

                //////////
                DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
                root.child("chatlatest").child(room_Key).child("Message").setValue(input_msg.getText().toString());
                root.child("chatlatest").child(room_Key).child("name").setValue(user_name);
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                root.child("chatlatest").child(room_Key).child("TIMESTAMP").setValue(ts);

                input_msg.setText("");

            }
        });



        if (Build.VERSION.SDK_INT >= 11) {
            recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v,
                                           int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bottom < oldBottom) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new ThreadAdapter(Chat_Room.this, messages, user_name);
                                recyclerView.setAdapter(adapter);
                                recyclerView.scrollToPosition(
                                        recyclerView.getAdapter().getItemCount() - 1);
                            }
                        }, 100);
                    }
                }
            });
        }
     //   setupToolBar("Talk to us");
    }

    private String chat_msg, chat_user_name, time;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot) i.next()).getValue();
            time = (String) ((DataSnapshot) i.next()).getValue();

            Message messagObject = new Message(chat_user_name, chat_msg, time);
            messages.add(messagObject);

            adapter = new ThreadAdapter(Chat_Room.this, messages, user_name);
            recyclerView.setAdapter(adapter);
            scrollToBottom();
        }
    }

    private void scrollToBottom() {

        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() > 1){
            recyclerView.getLayoutManager().scrollToPosition(adapter.getItemCount()-1);
        }
    }
    @Override
    public void onBackPressed() {
        // your code.
        Intent intent2 = new Intent(Chat_Room.this, FriendChatlistActivity.class);
        startActivity(intent2);
        finish();

    }
//    private void setupToolBar(String title) {
//        View view = getLayoutInflater().inflate(R.layout.action_bar, null);
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER);
//
//        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
//        Title.setText(title);
//
//        getSupportActionBar().setCustomView(view, params);
//        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
//        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_menu_add:
//            case android.R.id.home:
//                finish();
//                overridePendingTransition(R.anim.stay, R.anim.slide_down);
//                break;
//        }
//        return true;
//    }
//    public void onBackPressed() {
//        finish();
//        overridePendingTransition(R.anim.stay, R.anim.slide_down);
//    }
}
