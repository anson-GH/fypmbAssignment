package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SellerlistActivity extends AppCompatActivity {
    ListView mListView;
    //  ArrayAdapter<String> adapter;
    ArrayList<String> listtvName = new ArrayList<>();
    ArrayList<String> listtvPrice = new ArrayList<>();
    ArrayList<String> listivImage = new ArrayList<>();
    private ProgressDialog mProgress;
    // positionRow p ;
    CustomListSalesAdapter adapter;
    String productlist;
    String[] classAll;
    SalesClass[] salesClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerlist);


        adapter = new CustomListSalesAdapter(getApplicationContext(), null);
        mListView = (ListView) findViewById(R.id.mListView);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot();




        databaseReference.child("student").child("12AAD1212").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // String value= dataSnapshot.getValue(String.class).toString();
                Map<String, Object> map = (Map) dataSnapshot.getValue();
                productlist = map.get("productlist").toString();

                String a  ;
                a = productlist.substring(0, productlist.length() - 1);
                classAll = productlist.split(",");

                System.out.println(productlist);

                for(int i =0;i< classAll.length;i++) {
                    System.out.println(classAll[i]);
                }

                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference2 = database2.getReference().getRoot();
                databaseReference2.child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> it = dataSnapshot.getChildren();    //beauty

                        while (it.iterator().hasNext()) {
                            DataSnapshot ds = it.iterator().next();                //beauty
                           Iterable<DataSnapshot> its = ds.getChildren();         // list of key


                            while (its.iterator().hasNext()) {
                                DataSnapshot dss = its.iterator().next();           //key


                                for(int i =0;i< classAll.length;i++) {
                                if (classAll[i].equals(dss.getKey().toString())){
                                   // System.out.println("bb.   " + dss.child("Item name").getValue().toString());

                                    listtvName.add(dss.child("Item name").getValue().toString());
                                    listivImage.add(dss.child("Image1").getValue().toString());
                             //       listtvPrice.add(dss.child("price").getValue().toString());
                                }
                             }
                                salesClass = new SalesClass[listtvName.size()];
                                for(int j = 0;j<listtvName.size();j++){
                                    salesClass[j] = new SalesClass(listtvName.get(j), listivImage.get(j));
                                }
                                adapter = new CustomListSalesAdapter(getBaseContext(), salesClass);
                                mListView.setAdapter(adapter);

                                adapter.notifyDataSetChanged();
                            }
                        }
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



//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String Slecteditem= listitemname.get(position);
//
//                // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent (SellerlistActivity.this, ProductViewActivity.class);
//                intent.putExtra("EXTRA_SESSION_ID", Slecteditem);
//                startActivity(intent);
//            }
//        });



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
