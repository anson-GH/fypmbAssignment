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

public class SalesListActivity extends AppCompatActivity {
    ListView mListView;
    CustomListSalesAdapter adapter;
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
        databaseReference.child("Category").child("Mensfashion").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // String value= dataSnapshot.getValue(String.class).toString();
              //  Map<String, Object> map = (Map) dataSnapshot.getValue();
            //   String name = map.get("Item name").toString();

                listKey.add(dataSnapshot.getKey().toString());
                listtvName.add(dataSnapshot.child("Item name").getValue().toString());
                listtvPrice.add(dataSnapshot.child("Price").getValue().toString());
                listDescription.add(dataSnapshot.child("Description").getValue().toString());
                listCondition.add(dataSnapshot.child("Condition").getValue().toString());
                listCategory.add(dataSnapshot.child("Category").getValue().toString());
                listLocation.add(dataSnapshot.child("Location").getValue().toString());
                listivImage1.add(dataSnapshot.child("Image1").getValue().toString());
                listivImage2.add(dataSnapshot.child("Image2").getValue().toString());
                listivImage3.add(dataSnapshot.child("Image3").getValue().toString());
                listTimestamp.add(dataSnapshot.child("Timestamp").getValue().toString());
                listStatus.add(dataSnapshot.child("Status").getValue().toString());
                listStudentID.add(dataSnapshot.child("Student id").getValue().toString());


                salesClass = new SalesClass[listtvName.size()];
                for(int j = 0;j<listtvName.size();j++){
                    if(listStatus.get(j).toString().equals("SALE")){
                    salesClass[j] = new SalesClass(listKey.get(j) ,listtvName.get(j),listtvPrice.get(j),listDescription.get(j),listCondition.get(j),listCategory.get(j),
                            listLocation.get(j),listivImage1.get(j),listivImage2.get(j),listivImage3.get(j),listTimestamp.get(j),listStatus.get(j),listStudentID.get(j));
                    }
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

                            if(itemStudID.equals("12AAD1212")) {
                                Intent intent2 = new Intent(SalesListActivity.this, ProductSellerViewActivity.class);
                                intent2.putExtra("itemKeyPass", itemKey);
                                intent2.putExtra("itemNamePass", itemName);
                                intent2.putExtra("itemPricePass", itemPrice);
                                intent2.putExtra("itemDescriptionPass", itemDescription);
                                intent2.putExtra("itemConditionPass", itemCondition);
                                intent2.putExtra("itemCategoryPass", itemCategory);
                                intent2.putExtra("itemImage1Pass", itemImage1);
                                intent2.putExtra("itemImage2Pass", itemImage2);
                                intent2.putExtra("itemImage3Pass", itemImage3);
                                intent2.putExtra("itemLocationPass", itemLocation);
                                intent2.putExtra("itemTimestampPass", itemTimestamp);
                                intent2.putExtra("itemStatusPass", itemStatus);
                                intent2.putExtra("itemStudIDPass", itemStudID);
                                startActivity(intent2);

                            }else{
                                Intent intent = new Intent (SalesListActivity.this, ProductViewActivity.class);
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
