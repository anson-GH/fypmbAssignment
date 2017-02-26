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
import java.util.HashSet;

public class AnnounceListLectActivity extends AppCompatActivity {
    ListView mListView;
    // ArrayAdapter<String> adapter;
    ArrayList<String> listTitle = new ArrayList<>();
    ArrayList<String> listMessage = new ArrayList<>();
    ArrayList<String> listCourse = new ArrayList<>();
    ArrayList<String> listTime = new ArrayList<>();
    ArrayList<String> listDate = new ArrayList<>();
    ArrayList<String> listLecturer = new ArrayList<>();
    ArrayList<String> values=new ArrayList<String>();
    HashSet<String> hashSet = new HashSet<String>();
    ArrayList<String> listKey = new ArrayList<>();
    ArrayList<String> listKeyDb = new ArrayList<>();
    ArrayList<String> listcgroup = new ArrayList<>();

    private ProgressDialog mProgress;
    // positionRow p ;
    CustomListAnnounceAdapter adapter;
    DatabaseReference databaseReference;
    AnnounceClass[] account;
   private String tempKey2;
    private String tempKey3;
    private String tempKey4;
    private String tempKey5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_list_lect);


//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Loading");
//        mProgress.show();
        adapter = new CustomListAnnounceAdapter(getApplicationContext(), null);
        mListView = (ListView) findViewById(R.id.mListViewv);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().getRoot();
        databaseReference.child("Announcement").child("12WAR1333").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> it = dataSnapshot.getChildren(); //12war

                while(it.iterator().hasNext()){
                    DataSnapshot ds = it.iterator().next();
                    Iterable<DataSnapshot> it1 = ds.getChildren();      //agile

                    while(it1.iterator().hasNext()){
                        DataSnapshot ds1 = it1.iterator().next();
                        Iterable<DataSnapshot> it2 = ds1.getChildren();   //rsdgroup

                        while(it2.iterator().hasNext()){
                            DataSnapshot ds3 = it2.iterator().next();       //key

                        if(!hashSet.contains(ds3.getKey())){
                            hashSet.add(ds3.getKey());
                          listKey.add(ds3.getKey().toString());
                       //     System.out.println(ds3.getKey().toString());
                            listTitle.add(ds3.child("Title").getValue().toString());
                            listMessage.add(ds3.child("Message").getValue().toString());
                            listCourse.add(ds3.child("Course").getValue().toString());
                            listTime.add(ds3.child("Time").getValue().toString());
                            listDate.add(ds3.child("Date").getValue().toString());
                            listLecturer.add(ds3.child("name").getValue().toString());
                            listKeyDb.add(ds3.child("key").getValue().toString());
                            listcgroup.add(ds3.child("Classgroup").getValue().toString());

                        }
               }
                    }
                    //refresh listview
                    account = new AnnounceClass[listTitle.size()];
                    for(int i = 0;i<listTitle.size();i++){
                        account[i] = new AnnounceClass(listCourse.get(i), listTitle.get(i),listTime.get(i),listDate.get(i));
                    }


                    ArrayList<AnnounceClass> list = new ArrayList<AnnounceClass>();
                    for(int i = 0;i<listTitle.size();i++){
                        list.add(account[i]);
                    }
                    adapter = new CustomListAnnounceAdapter(getBaseContext(), account);
                    mListView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
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
                String Title = listTitle.get(position);
                String Course = listCourse.get(position);
                String Message = listMessage.get(position);
                String keylist = listKey.get(position);
                String classroup = listcgroup.get(position);
                String keydb = listKeyDb.get(position);
                // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AnnounceListLectActivity.this, AnnounceEditActivity.class);
                intent.putExtra("TitlePass", Title);
                intent.putExtra("CoursePass", Course);
                intent.putExtra("MessagePass", Message);
                intent.putExtra("KeyPass", keylist);
                intent.putExtra("CgroupPass", classroup);
                intent.putExtra("KeydbPass", keydb);
                startActivity(intent);
            }
        });

    }

}
