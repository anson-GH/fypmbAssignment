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
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class AnnouncementListActivity extends AppCompatActivity {
    ListView mListView;
   // ArrayAdapter<String> adapter;
    ArrayList<String> listTitle = new ArrayList<>();
    ArrayList<String> listMessage = new ArrayList<>();
    ArrayList<String> listCourse = new ArrayList<>();
    ArrayList<String> listTime = new ArrayList<>();
    ArrayList<String> listDate = new ArrayList<>();
    ArrayList<String> listLecturer = new ArrayList<>();

    private ProgressDialog mProgress;
    // positionRow p ;
    CustomListAnnounceAdapter adapter;
    DatabaseReference databaseReference;
    AnnounceClass[] account;
    String tempKey2;
    String tempKey3;
    String tempKey4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_list);


//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Loading");
//        mProgress.show();
        adapter = new CustomListAnnounceAdapter(getApplicationContext(), null);
    //    adapter = new CustomListAnnounceAdapter(this, listTitle, listCourse,listLecturer,listMessage, listDate, listTime);
        mListView = (ListView) findViewById(R.id.mListView);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
          databaseReference = database.getReference().getRoot();
        databaseReference.child("Announcement").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                 tempKey2 = dataSnapshot.getKey().toString();

                   System.out.println("1     " + tempKey2);

                         databaseReference.child("Announcement").child(tempKey2).addChildEventListener(new ChildEventListener(){

                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                                tempKey3 = dataSnapshot.getKey().toString();
                                System.out.println("2     " + tempKey3);
                                databaseReference.child("Announcement").child(tempKey2).child(tempKey3).child("RSDgroup1").addChildEventListener(new ChildEventListener(){

                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        tempKey4 = dataSnapshot.getKey().toString();
                                        System.out.println("3     " + tempKey4);

                                        //    for (DataSnapshot keys: dataSnapshot.getChildren()) {
                                        //    String electionName = keys.getValue(String.class).toString();
                                        //       System.out.println("hasddda     " + electionName);

                                        Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();

                                        String Message = newPost.get("Message").toString();
                                        String Title = newPost.get("Title").toString();
                                        String Name = newPost.get("name").toString();
                                        String Course = newPost.get("Course").toString();
                                        String TimeD = newPost.get("Time").toString();
                                        String DateD = newPost.get("Date").toString();

                                        listTitle.add(Title);
                                       listLecturer.add(Name);
                                       listDate.add(DateD);
                                        listTime.add(TimeD);
                                       listCourse.add(Course);
                                        listMessage.add(Message);
                             //           adapter.notifyDataSetChanged();
                                        //  }
                                       account = new AnnounceClass[listTitle.size()];
                                        for(int i = 0;i<listTitle.size();i++){
                                            account[i] = new AnnounceClass(listCourse.get(i), listTitle.get(i),listTime.get(i),listDate.get(i),"",listMessage.get(i),listLecturer.get(i),"");
                                        }


                                        ArrayList<AnnounceClass> list = new ArrayList<AnnounceClass>();
                                        for(int i = 0;i<listTitle.size();i++){
                                            list.add(account[i]);
                                        }

                                        Collections.sort(list, new Comparator() {
                                            @Override
                                            public int compare(Object o1, Object o2) {
                                                AnnounceClass p1 = (AnnounceClass) o1;
                                                AnnounceClass p2 = (AnnounceClass) o2;
                                                String DT1 = p1.getDateD() + p1.getTimeD() ;
                                                String DT2 = p2.getDateD() + p2.getTimeD() ;

                                                return DT2.compareToIgnoreCase(DT1);
                                            }
                                        });
                                        for(int i = 0;i<listTitle.size();i++){
                                            account[i] = list.get(i);
                                        }
                                        adapter = new CustomListAnnounceAdapter(getBaseContext(), account);
                                        mListView.setAdapter(adapter);


                                    }

                                    @Override
                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
                            }
                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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



                // String value= dataSnapshot.getValue(String.class).toString();
                //                Map<String, Object> map = (Map) dataSnapshot.getValue();
                //                name = map.get("item name").toString();
                //                String   price = map.get("Title").toString();
                //                String   img1 = map.get("Course").toString();
                //                String   price = map.get("price").toString();
                //                String   img1 = map.get("image1").toString();
                //
                //                listTitle.add(name);
                //                listName.add(price);
                //                listDate.add(name);
                //                listTime.add(price);
                //                adapter.notifyDataSetChanged();
                //                mProgress.dismiss();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String Title= account[position].getTitle();
                String Course= account[position].getCourse();

                // Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AnnouncementListActivity.this, AnnounceViewActivity.class);
                intent.putExtra("TitlePass", Title);
                intent.putExtra("CoursePass", Course);

                startActivity(intent);
            }
        });

    }


}