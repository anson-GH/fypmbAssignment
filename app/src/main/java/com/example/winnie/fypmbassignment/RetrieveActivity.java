package com.example.winnie.fypmbassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class RetrieveActivity extends AppCompatActivity {


TextView tvsomething,tvdownload,tvname,tvcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        tvcity = (TextView) findViewById(R.id.tvcity);
        tvdownload = (TextView) findViewById(R.id.tvdownload);
        tvname = (TextView) findViewById(R.id.tvname);
        tvsomething = (TextView) findViewById(R.id.tvsomething);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Product").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot keys : snapshot.getChildren()) {
                    //String to temporarily store the whole child of the inidividual user's DB node ----> It still produces different order of the keys

                    Map<String, String> map = (Map)keys.getValue();
                    String tempKey = map.toString();
                    //The problem persists if I code it like this as well.
                  //  String tempKey2 = snapshot.child(keys.getKey()).getValue().toString();
                  //  tvcity.setText(tempKey2);

                    String city = map.get("city");
                //    String download = map.get("download");
               //     String name = map.get("name");
                //    String something = map.get("something");

   System.out.println(city);
                //    tvcity.setText(city);
//                    tvname.setText(name);
//                    tvdownload.setText(download);
//                    tvsomething.setText(something);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        databaseReference.child("Product").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, String> map = (Map)dataSnapshot.getValue(); // Working
//
//                String city = map.get("name");
//                String download = map.get("download");
//                String name = map.get("name");
//                String something = map.get("something");
//
//
//               tvcity.setText(city);
//                tvname.setText(name);
//               tvdownload.setText(download);
//               tvsomething.setText(something);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }
}
