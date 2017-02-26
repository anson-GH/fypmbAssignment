package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnnounceEditActivity extends AppCompatActivity {
    Button buttonUpdate,btn_clearTitle,btn_clearbox;
    TextView textCourse,textTitle,etMessageBox,textClass;
    String Course,Title,Message,KeyP,classgroup,keydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_edit);

        textClass = (TextView) findViewById(R.id.textCourse);
        textCourse = (TextView) findViewById(R.id.textCourse);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        etMessageBox = (TextView) findViewById(R.id.etMessageBox);
        btn_clearbox = (Button) findViewById(R.id.btn_clearbox);

        textTitle = (TextView) findViewById(R.id.textTitle);
        btn_clearTitle = (Button) findViewById(R.id.btn_clearTitle);

        Title = getIntent().getStringExtra("TitlePass");
        Course = getIntent().getStringExtra("CoursePass");
        Message = getIntent().getStringExtra("MessagePass");
        KeyP = getIntent().getStringExtra("KeyPass");
        classgroup = getIntent().getStringExtra("CgroupPass");
        keydb = getIntent().getStringExtra("KeydbPass");


        textTitle.setText(Title);
        textCourse.setText(Course);
        textClass.setText(classgroup);
        etMessageBox.setText(Message);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (R.id.buttonUpdate == view.getId()) {


                    SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                    Date todayDate = new Date();
                    String thisDate = currentDate.format(todayDate);

                    SimpleDateFormat sdf5 = new SimpleDateFormat("h:mm");
                    String currentDateandTime = sdf5.format(new Date());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                 DatabaseReference databaseReference = database.getReference().getRoot().child("Announcement").child("12WAR1333");
                    //  String pushkey = databaseReference.push().getKey();

                    databaseReference.child(Course).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //    String tempKey =  dataSnapshot.getKey().toString();
                        //System.out.println("3   "+tempKey);

                            String a  ;
                            a = classgroup.substring(0, classgroup.length() - 1);
                            String[] classAll = classgroup.split(",");


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference().getRoot().child("Announcement").child("12WAR1333");

                            for(int i =0;i< classAll.length;i++) {
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Title").setValue(textTitle.getText().toString());
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Message").setValue(etMessageBox.getText().toString());
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("name").setValue("Ms. tan");
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Course").setValue(Course);
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Date").setValue("w");
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Time").setValue("we");
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("Classgroup").setValue(classgroup);
                                databaseReference.child(Course).child(classAll[i]).child(KeyP).child("key").setValue(KeyP);


                            }

                                Toast.makeText(AnnounceEditActivity.this, "Successfuly ", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(AnnounceEditActivity.this, AnnounceListLectActivity.class);
                            startActivity(intent);
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

}
});
        }
}

