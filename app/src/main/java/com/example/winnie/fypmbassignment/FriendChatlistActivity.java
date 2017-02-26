package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.winnie.fypmbassignment.chat.Chat_Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class FriendChatlistActivity extends AppCompatActivity {
   FloatingActionButton buttonplus,buttonAddfren;
  //  ListView mListView;
    Animation fab_scale_down,fab_scale_up,fab_Clockwise,fab_Anticlockwise;
    boolean isOpen = false;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().getRoot().child("chatlatest");
    DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().getRoot();
    private DatabaseReference database3 = FirebaseDatabase.getInstance().getReference().getRoot();
    private ListView mListView;
//    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private ArrayList<String> chatName = new ArrayList<>();
    private ArrayList<String> chatID = new ArrayList<>();
    private ArrayList<String> chatKey = new ArrayList<>();
    private ArrayList<String> chatImage = new ArrayList<>();
    private ArrayList<String> chatMessage = new ArrayList<>();
    private ArrayList<String> chatTimeDate = new ArrayList<>();

     CustomListChatAdapter adapter;
    DatabaseReference databaseReference;
  //  ChatClass[] chatclass;
  //  ChatNameClass[] chatnameclass;
  ArrayList<ChatClass> chatclass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_chatlist);

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
                Intent intent = new Intent(FriendChatlistActivity.this, FriendSearchlistActivity.class);
                startActivity(intent);
            }
        });


        mListView = (ListView) findViewById(R.id.mListView);
//        adapter = new CustomListChatAdapter(getApplicationContext(), null);


        database2.child("student").child("12AAD1212").child("friendlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatID.removeAll(chatID);
                chatName.removeAll(chatName);
                chatKey.removeAll(chatKey);
                chatImage.removeAll(chatImage);


                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                while (it.iterator().hasNext()) {
                    final DataSnapshot ds = it.iterator().next();

                    Map<String, Object> newPost = (Map<String, Object>) ds.getValue();
                    String idget = newPost.get("id").toString();
                    String nameget = newPost.get("name").toString();

                    System.out.println("friendlistss     "+ds.getKey().toString());
                    System.out.println("friend id     "+idget);
                    System.out.println("friend name     "+nameget);

                    chatID.add(idget);
                    chatName.add(nameget);
                    chatKey.add(ds.getKey().toString());
                    //   friendAll.add(ds.getKey().toString());


                    //   }

                    //   chatnameclass = new ChatNameClass[chatKey.size()];
                    //      for(int i = 0;i<chatKey.size();i++){

                    //         ChatNameClass  chatnameclass1 = new ChatNameClass(chatKey.get(i), chatName.get(i),chatID.get(i));
                    //         chatnameclass.add(chatnameclass1);
                    //         System.out.println("chatnameclass    "+ chatnameclass.get(i));


                    database3.child("student").child(idget).child("city").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            chatMessage.removeAll(chatMessage);
                            chatTimeDate.removeAll(chatTimeDate);

                            System.out.println("city     " + dataSnapshot.getValue().toString());
                            chatImage.add(dataSnapshot.getValue().toString());

                            database.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    //  Set<String> set = new HashSet<String>();
                                    // Iterator it = dataSnapshot.getChildren();
                                    Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                                    System.out.println("asdadasdasdasdasdasd");


                                    while (it.iterator().hasNext()) {
                                        DataSnapshot dss = it.iterator().next();


                                        //   for(int j =0;j< chatnameclass.size();j++) {

                                        if(ds.getKey().toString().contains(dss.getKey().toString())) {
                                            System.out.println("friendlists     " + dss.getKey().toString());

                                            chatMessage.add(dss.child("Message").getValue().toString());
                                            chatTimeDate.add(dss.child("TIMESTAMP").getValue().toString());
                                            System.out.println("message    "+ dss.child("Message").getValue().toString());
                                            System.out.println("TIMESTAMP    "+ dss.child("TIMESTAMP").getValue().toString());

                                        }

                                        //    }

                                        //    Set<ChatClass> list = new HashSet<ChatClass>();
//                            for(int i = 0;i<chatName.size();i++){
//                                list.add(chatclass[i]);
//                            }
//
//                            adapter = new CustomListChatAdapter(getBaseContext(), chatclass);
//                            mListView.setAdapter(adapter);
//
//                            adapter.notifyDataSetChanged();
                                        System.out.println("chatKey22     " + chatKey);
                                        System.out.println("chatID22     " + chatID);
                                        System.out.println("chatName22     " + chatName);
                                        System.out.println("city22     " + chatImage);
                                        System.out.println("chatMessage22    " + chatMessage);
                                        System.out.println("chatTimeDate22     " + chatTimeDate);
                                        System.out.println("chatclass22     " + chatclass);
                                       chatclass.removeAll(chatclass);
                                    }
                                    System.out.println("chatKey222     " + chatKey);
                                    System.out.println("chatID222     " + chatID);
                                    System.out.println("chatName222     " + chatName);
                                    System.out.println("city222     " + chatImage);
                                    System.out.println("chatMessage222    " + chatMessage);
                                    System.out.println("chatTimeDate222     " + chatTimeDate);
                                    System.out.println("chatclass222     " + chatclass);

                                    for(int i = 0;i<chatTimeDate.size()&&chatImage.size()==chatTimeDate.size();i++) {

                                        ChatClass chatclass1 = new ChatClass(chatName.get(i), chatImage.get(i), chatTimeDate.get(i), chatMessage.get(i));
                                            chatclass.add(chatclass1);
                                            System.out.println("chatclass    " + chatclass.get(i));
                                            //   set.add(ds.getKey().toString());

                                    }


                                    ArrayList<ChatClass> list = new ArrayList<ChatClass>();
                                    for(int i = 0;i<chatclass.size();i++){
                                        list.add(chatclass.get(i));
                                    }

                                    Collections.sort(list, new Comparator() {
                                        @Override
                                        public int compare(Object o1, Object o2) {
                                            ChatClass p1 = (ChatClass) o1;
                                            ChatClass p2 = (ChatClass) o2;
                                            String DT1 = p1.getChatTimeDate()  ;
                                            String DT2 = p2.getChatTimeDate()  ;

                                            return DT2.compareToIgnoreCase(DT1);
                                        }
                                    });
                                    chatclass.removeAll(chatclass);
                                    for(int i = 0;i<list.size();i++){
                                        chatclass.add(list.get(i));
                                    }

                                    adapter = new CustomListChatAdapter(getBaseContext(), chatclass);
                                    mListView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();


                                    //  list_of_rooms.clear();
                                    //  list_of_rooms.addAll(set);

                                    //  Set<String> set = new HashSet<String>();

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
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                //  intent.putExtra("room_name", ((TextView) view).getText().toString());
                String chatKeyPass= chatKey.get(position);
                String chatNamePass= chatName.get(position);

                Intent intent = new Intent(getApplicationContext(), Chat_Room.class);

                intent.putExtra("room_Key", chatKeyPass);
                intent.putExtra("friend_name", chatNamePass);
                intent.putExtra("user_name", "John");
                startActivity(intent);
                finish();
            }
        });


    }


}
