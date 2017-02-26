package com.example.winnie.fypmbassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchfriendActivityfromdb extends AppCompatActivity {
    EditText textSearch;
    Button btSearch;
   // CustomListFriendAdapter adapter;
    DatabaseReference databaseReference;
    ListView mListViewv;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<String> listCity = new ArrayList<>();
    ArrayList<String> listImage = new ArrayList<>();

    FriendClass[] friendc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfrienddb);

        textSearch = (EditText) findViewById(R.id.textSearch);
        btSearch = (Button) findViewById(R.id.btSearch);

    //    adapter = new CustomListFriendAdapter(getApplicationContext(), null);
        mListViewv = (ListView) findViewById(R.id.mListViewv);

      btSearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              listName.removeAll(listName);
              listCity.removeAll(listCity);
            //  listImage.removeAll(listImage);

              String searchname =textSearch.getText().toString();
              DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
              Query query = databaseReference.child("student").orderByChild("student name").startAt(searchname).endAt(searchname+"\uf8ff");;

              query.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                      for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                          //TODO get the data here
                          listName.add(postSnapshot.child("student name").getValue().toString());
                         listCity.add(postSnapshot.child("city").getValue().toString());

                                 System.out.println("1  " + postSnapshot.child("student name").getValue().toString());

                               }

                          //refresh listview
                        }else{
                      listName.add("no result");
                      listCity.add(" ");
                        }

                      friendc = new FriendClass[listName.size()];
                          for (int i = 0; i < listName.size(); i++) {
                         //     friendc[i] = new FriendClass(listName.get(i),listCity.get(i),listCity.get(i), "");
                          }


                          ArrayList<FriendClass> list = new ArrayList<FriendClass>();
                          for (int i = 0; i < listName.size(); i++) {
                              list.add(friendc[i]);
                          }
                //          adapter = new CustomListFriendAdapter(getBaseContext(), friendc);
                  //        mListViewv.setAdapter(adapter);

                   //       adapter.notifyDataSetChanged();

                  }
                  @Override
                  public void onCancelled(DatabaseError databaseError) {

                  }
              });
          }
      });
    }
}
