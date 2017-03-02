package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerlistActivity extends AppCompatActivity {
    ListView mListView;
    //  ArrayAdapter<String> adapter;
    ArrayList<String> listtvName = new ArrayList<>();
    ArrayList<String> listtvPrice = new ArrayList<>();
    ArrayList<String> listivImage1 = new ArrayList<>();
    ArrayList<String> listivImage2 = new ArrayList<>();
    ArrayList<String> listivImage3 = new ArrayList<>();
    ArrayList<String> listKey = new ArrayList<>();
    ArrayList<String> listDescription = new ArrayList<>();
    ArrayList<String> listCondition = new ArrayList<>();
    ArrayList<String> listCategory= new ArrayList<>();
    ArrayList<String> listLocation= new ArrayList<>();
    ArrayList<String> listTimestamp= new ArrayList<>();
    ArrayList<String> listStatus= new ArrayList<>();
    ArrayList<String> listStudentID= new ArrayList<>();

    ArrayList<String> productlist = new ArrayList<>();

    private ProgressDialog mProgress;
    // positionRow p ;
    CustomListSalesAdapter adapter;
    String[] classAll;
    SalesClass[] salesClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerlist);


      //  adapter = new CustomListSalesAdapter(getApplicationContext(), null);
        mListView = (ListView) findViewById(R.id.mListView);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot();




        databaseReference.child("student").child("12AAD1212").child("productlist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        // String value= dataSnapshot.getValue(String.class).toString();
                        //    Map<String, Object> map = (Map) dataSnapshot.getValue();
                        //   productlist = map.get("productlist").toString();
                        productlist.add(postSnapshot.getKey().toString());

                        System.out.println("1.   " + productlist);


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


                                for(int i =0;i< productlist.size();i++) {
                                if (productlist.get(i).toString().equals(dss.getKey().toString())){
                                    listKey.add(dss.getKey().toString());
                                    listtvName.add(dss.child("Item name").getValue().toString());
                                    listtvPrice.add(dss.child("Price").getValue().toString());
                                    listDescription.add(dss.child("Description").getValue().toString());
                                    listCondition.add(dss.child("Condition").getValue().toString());
                                    listCategory.add(dss.child("Category").getValue().toString());
                                    listLocation.add(dss.child("Location").getValue().toString());
                                    listivImage1.add(dss.child("Image1").getValue().toString());
                                    listivImage2.add(dss.child("Image2").getValue().toString());
                                    listivImage3.add(dss.child("Image3").getValue().toString());
                                    listTimestamp.add(dss.child("Timestamp").getValue().toString());
                                    listStatus.add(dss.child("Status").getValue().toString());
                                    listStudentID.add(dss.child("Student id").getValue().toString());
                                }
                             }
                                salesClass = new SalesClass[listtvName.size()];
                                for(int j = 0;j<listtvName.size();j++){
       salesClass[j] = new SalesClass(listKey.get(j) ,listtvName.get(j),listtvPrice.get(j),listDescription.get(j),listCondition.get(j),listCategory.get(j),
               listLocation.get(j),listivImage1.get(j),listivImage2.get(j),listivImage3.get(j),listTimestamp.get(j),listStatus.get(j),listStudentID.get(j));
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
                String itemKey= salesClass[position].getIdKey();
                String itemName= salesClass[position].getName();
                String itemPrice= salesClass[position].getPrice();
                String itemDescription= salesClass[position].getDescription();
                String itemCondition= salesClass[position].getCondition();
                String itemCategory= salesClass[position].getCategory();
                String itemImage1= salesClass[position].getImage1();
                String itemImage2= salesClass[position].getImage2();
                String itemImage3= salesClass[position].getImage3();
                String itemLocation= salesClass[position].getLocation();
                String itemTimestamp= salesClass[position].getTimestamp();
                String itemStatus= salesClass[position].getStatus();
                String itemStudID= salesClass[position].getStudID();

                // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (SellerlistActivity.this, ProductSellerViewActivity.class);
                intent.putExtra("itemKeyPass", itemKey);
                intent.putExtra("itemNamePass", itemName);
                intent.putExtra("itemPricePass", itemPrice);
                intent.putExtra("itemDescriptionPass", itemDescription);
                intent.putExtra("itemConditionPass", itemCondition);
                intent.putExtra("itemCategoryPass", itemCategory);
                intent.putExtra("itemImage1Pass", itemImage1);
                intent.putExtra("itemImage2Pass", itemImage2);
                intent.putExtra("itemImage3Pass", itemImage3);
                intent.putExtra("itemLocationPass", itemLocation);
                intent.putExtra("itemTimestampPass", itemTimestamp);
                intent.putExtra("itemStatusPass", itemStatus);
                intent.putExtra("itemStudIDPass", itemStudID);

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
