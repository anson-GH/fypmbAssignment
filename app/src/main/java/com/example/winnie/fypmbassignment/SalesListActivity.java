package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class SalesListActivity extends AppCompatActivity {
    ListView mListView;
    CustomListSalesAdapter adapter;
  ArrayList<String> listtvName = new ArrayList<>();
    ArrayList<String> listtvPrice = new ArrayList<>();
    ArrayList<String> listivImage = new ArrayList<>();
private ProgressDialog mProgress;
    // positionRow p ;
    SalesClass[] salesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);

//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Loading");
//        mProgress.show();

        adapter = new CustomListSalesAdapter(getApplicationContext(), null);
        mListView = (ListView) findViewById(R.id.mListView);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot();

     //   adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        String getgridchosen = getIntent().getStringExtra("salesclick");

System.out.println("1             "+getgridchosen);
        databaseReference.child("Category").child("Beauty").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // String value= dataSnapshot.getValue(String.class).toString();
                Map<String, Object> map = (Map) dataSnapshot.getValue();
               String name = map.get("Item name").toString();
          //   String   price = map.get("price").toString();
     //        String   img1 = map.get("Image1").toString();
                String   img1 = null;
                listtvName.add(name);
                listivImage.add(img1);
                //       listtvPrice.add(dss.child("price").getValue().toString());

                salesClass = new SalesClass[listtvName.size()];
                for(int j = 0;j<listtvName.size();j++){
                    salesClass[j] = new SalesClass(listtvName.get(j), listivImage.get(j));

                }

                adapter = new CustomListSalesAdapter(getBaseContext(), salesClass);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
             //   mProgress.dismiss();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
              //  list.remove(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                String Slecteditem= listtvName.get(position);

               // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (SalesListActivity.this, ProductViewActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", Slecteditem);
                startActivity(intent);
            }
        });



//        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, databaseReference) {
//            @Override
//            protected void populateView(View v, String model, int position) {
//                TextView textView = (TextView) v.findViewById(android.R.id.text1);
//                textView.setText(model);
//            }
//        };
//        mListView.setAdapter(firebaseListAdapter);

    }










}
