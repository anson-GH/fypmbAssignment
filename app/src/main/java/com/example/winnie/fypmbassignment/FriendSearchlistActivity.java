package com.example.winnie.fypmbassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class FriendSearchlistActivity extends Activity {

    // Declare Variables
    ListView listviewF;
    CustomListFrenAdapter adapter;
    EditText editsearch;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<String> listImage = new ArrayList<>();
    ArrayList<String> listPosition = new ArrayList<>();
    ArrayList<String> listUserID = new ArrayList<>();
   // ArrayList<Integer> flag = new ArrayList<>();
   DatabaseReference databaseReference,databaseReference2;
    ArrayList<FriendClass> arraylist = new ArrayList<FriendClass>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlistfren);

        // Locate the ListView in listview_main.xml
        listviewF = (ListView) findViewById(R.id.listview);
        editsearch = (EditText) findViewById(R.id.search);
        listviewF.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().getRoot();
        databaseReference2 = database.getReference().getRoot();

        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   if (dataSnapshot.exists()) {
                       for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        System.out.println("1id    "+ postSnapshot.getKey().toString());

                        listName.add(postSnapshot.child("name").getValue().toString());
                           String idS = postSnapshot.child("id").getValue().toString();
                        listUserID.add(idS);
                           String positionS = postSnapshot.child("position").getValue().toString();
                        listPosition.add(positionS);

                           System.out.println("2name    "+ postSnapshot.child("name").getValue().toString());
                           System.out.println("3positionS   "+ positionS);

                           databaseReference2.child(positionS).child(idS).addListenerForSingleValueEvent(new ValueEventListener() {


                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   String imageS = dataSnapshot.child("imageprofile").getValue().toString();
                                   listImage.add(imageS);
                                   System.out.println("4imageS    "+ imageS);

                                   for (int i = 0; i < listName.size()&&listName.size()==listImage.size(); i++){
                                       FriendClass wp = new FriendClass(listName.get(i), listPosition.get(i),listUserID.get(i), listImage.get(i));
                                       arraylist.add(wp);
                                   }
                                   adapter = new CustomListFrenAdapter(getBaseContext(), arraylist);
                                   listviewF.setAdapter(adapter);
                                   adapter.notifyDataSetChanged();

                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });

                           //  listImage
                       }


                   }
               }
               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });

            // Pass results to ListViewAdapter Class

        // Binds the Adapter to the ListView


        // Locate the EditText in listview_main.xml

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                listviewF.setVisibility(View.VISIBLE);
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
               adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });


        listviewF.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String Name = adapter.getItem(position).getUsername();
                String Position = adapter.getItem(position).getUserposition();
                String UserID = adapter.getItem(position).getUserID();
             //   String Image = adapter.getItem(position).getStudentImage();
                // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(FriendSearchlistActivity.this, FriendProfileViewActivity.class);
                intent.putExtra("nameF", Name);
                intent.putExtra("PositionF", Position);
                intent.putExtra("idF", UserID);

                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        // your code.
        Intent intent2 = new Intent(FriendSearchlistActivity.this, FriendChatlistActivity.class);
        startActivity(intent2);
        finish();

    }
}
