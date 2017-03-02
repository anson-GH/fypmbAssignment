package com.example.winnie.fypmbassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AnnouncementActivity extends ActionBarActivity {
    String itemCourse ;
   Spinner spinnerCourse;
    List<String> CourseList = new ArrayList<String>();
//    List<String> classgroup = new ArrayList<String>();
//    ArrayAdapter<String> dataAdapter2;
    ArrayAdapter<String> dataAdapterCourse;

   Button buttonAdd,btn_clearTitle,btn_clearbox;

////////////////////


    TextView textClass,textTitle,etMessageBox;

    AlertDialog.Builder alertdialogbuilder;

List<String> AlertDialogItems = new ArrayList<String>();
    List<Boolean>  Selectedtruefalse= new ArrayList<>();
    boolean[] TrueFalseselect;
  //  List<String> ItemsIntoList = new ArrayList<String>();
//
//    boolean[] Selectedtruefalse = new boolean[]{
//            false,
//            false,
//            false,
//            false,
//            false,   false,
//            false,
//            false,
//            false,
//            false,   false,
//            false,
//            false
//
//    };



  /////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        textClass = (TextView) findViewById(R.id.textClass);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        etMessageBox = (TextView) findViewById(R.id.etMessageBox);
        btn_clearbox = (Button) findViewById(R.id.btn_clearbox);

        textTitle = (TextView) findViewById(R.id.textTitle);
        btn_clearTitle = (Button) findViewById(R.id.btn_clearTitle);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot();
        spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);

        dataAdapterCourse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CourseList);
        dataAdapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapterCourse);
        databaseReference.child("Staff").child("12WAR1333").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map) dataSnapshot.getValue();

           //     String classstaff = map.get("class").toString();
                String course = map.get("course").toString();

                // String  classGroup = map.get("class").toString();
             String[] coursesplit = course.split(",");

            for(int j = 0 ;j < coursesplit.length;j++){
                CourseList.add(coursesplit[j]);
               System.out.println("aaaaaaaaaaaaaaaaaaaa      " + CourseList );

            }

              //      StringTokenizer tokens = new StringTokenizer(classstaff, ",");
             //       String first = tokens.nextToken();
            //      String second = tokens.nextToken();
             //       System.out.println("aaaaaaaaaaaaaaaaaaaa      " + first + second);
              //  }

                dataAdapterCourse.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                itemCourse = parent.getItemAtPosition(position).toString();
                AlertDialogItems.removeAll(AlertDialogItems);

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + itemCourse, Toast.LENGTH_SHORT).show();
                textClass.setText("");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference().getRoot();
                databaseReference.child("class").child(itemCourse).child("12WAR1333").addChildEventListener(new ChildEventListener() {

             //       @Override
                //    public void onDataChange(DataSnapshot dataSnapshot) {

                        //       Map<String, Object> map = (Map) dataSnapshot.getValue(databaseReference.getKey());
                        //     for (DataSnapshot keys : dataSnapshot.getChildren()) {
                        //String to temporarily store the whole child of the inidividual user's DB node ----> It still produces different order of the keys
                     //   String tempKey = dataSnapshot.getKey().toString();
                   //     AlertDialogItems.add(tempKey);
                        //The problem persists if I code it like this as well.
                        //  String tempKey2 = dataSnapshot.child(keys.getKey()).getValue().toString();

                        //    }
                        // String course = map.get("course").toString();
                        // String  classGroup = map.get("class").toString();
                        //  String[] separated = course.split(",");

//                for (int i = 0; i < separated.length; i++) {
//                    //  if(separated[i].equals("|"+fromStation)){
//                    //  from = i+1;
//                    //  fromPrice = Integer.parseInt(separatedPrice[i]);
//                    //   fromDur = Integer.parseInt(separatedDuration[i]);
//                    AlertDialogItems.add(separated[i]);
//
//                    System.out.println("hahahxccczxczxczczxc     " + separated[i]);
//                    //   System.out.println("hahahxccczxczxczczxc     " + classgroup);
//
//                    // }
//                }
                        //dataAdapter2.notifyDataSetChanged();
                        //  dataAdapter2.notifyDataSetChanged();
             //           System.out.println("hahahahahaha00  adadasd      ");

             //       }

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String tempKey = dataSnapshot.getKey().toString();
                        AlertDialogItems.add(tempKey);
                   Selectedtruefalse.add(false);
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

                textClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        textClass.setText("");

                        alertdialogbuilder = new AlertDialog.Builder(AnnouncementActivity.this);

                        CharSequence[] charSeqOfNames = AlertDialogItems.toArray(new CharSequence[AlertDialogItems.size()]);

                    TrueFalseselect = new boolean[Selectedtruefalse.size()];
                        for (int i = 0; i < Selectedtruefalse.size(); i++) {
                            TrueFalseselect[i] = Selectedtruefalse.get(i);
                        }
                        alertdialogbuilder.setMultiChoiceItems(charSeqOfNames, TrueFalseselect , new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        });

                        alertdialogbuilder.setCancelable(false);

                        alertdialogbuilder.setTitle("Select Subjects Here");

                        alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int a = 0;
                                while (a < TrueFalseselect.length) {
                                    boolean value = TrueFalseselect[a];

                                    if (value) {

                                        textClass.setText( textClass.getText() + AlertDialogItems.get(a) +"," );

                                    }

                                    a++;
                                }

                            }
                        });

                        alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = alertdialogbuilder.create();

                        dialog.show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                if(R.id.buttonAdd== view.getId()){
String a =textClass.getText().toString() ;
                    a = a.substring(0, a.length() - 1);
                    String[] classAll = a.split(",");
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                    Date todayDate = new Date();
                    String thisDate = currentDate.format(todayDate);

                    SimpleDateFormat sdf5 = new SimpleDateFormat("h:mm");
                    String currentDateandTime = sdf5.format(new Date());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference().getRoot().child("Announcement").child("12WAR1333").child(itemCourse);
                    String pushkey = databaseReference.push().getKey();

                    for(int i =0;i< classAll.length;i++){
                       Toast.makeText(AnnouncementActivity.this, "Successfuly ", Toast.LENGTH_SHORT).show();

                        System.out.println("sdddddddddd     "+pushkey);
                        System.out.println("sdddddddddd     "+classAll[i]);
                 //     databaseReference.child(classAll[i]).child(pushkey);

                        databaseReference.child(classAll[i]).child(pushkey).child("Title").setValue(textTitle.getText().toString());
                    databaseReference.child(classAll[i]).child(pushkey).child("Message").setValue(etMessageBox.getText().toString());
                    databaseReference.child(classAll[i]).child(pushkey).child("name").setValue("Ms. tan");
                        databaseReference.child(classAll[i]).child(pushkey).child("Course").setValue(itemCourse);
                        databaseReference.child(classAll[i]).child(pushkey).child("Date").setValue(thisDate);
                        databaseReference.child(classAll[i]).child(pushkey).child("Time").setValue(currentDateandTime);
                        databaseReference.child(classAll[i]).child(pushkey).child("Classgroup").setValue(textClass.getText().toString());
                        databaseReference.child(classAll[i]).child(pushkey).child("key").setValue(pushkey);


                    }
                }
            }
            });

















    }


//////////////////////

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }











   //     FirebaseDatabase database = FirebaseDatabase.getInstance();
   //     DatabaseReference databaseReference = database.getReference().getRoot().child("Staff").child("12WAR1233");

//        databaseReference.child("class").setValue("RSD|group1,");
//        databaseReference.child("course").setValue("Agile,SE,");
//        databaseReference.child("name").setValue("Ms. tan");

//        buttonPublish = (Button) findViewById(R.id.buttonPublish) ;
//        buttonPublish.setOnClickListener(this);
//
//        spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
//        condition.add("Condition");
//        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, condition);
//        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference().getRoot();
//        spinnerCourse.setAdapter(dataAdapter2);
//        databaseReference.child("Staff").child("12WAR1233").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> map = (Map) dataSnapshot.getValue();
//
//                String course = map.get("course").toString();
//                // String  classGroup = map.get("class").toString();
//                String[] separated = course.split(",");
//
//                for (int i = 0; i < separated.length; i++) {
//                    //  if(separated[i].equals("|"+fromStation)){
//                    //  from = i+1;
//                    //  fromPrice = Integer.parseInt(separatedPrice[i]);
//                    //   fromDur = Integer.parseInt(separatedDuration[i]);
//                    condition.add(separated[i]);
//                    System.out.println("hahahxccczxczxczczxc     " + separated[i]);
//                    System.out.println("hahahxccczxczxczczxc     " + condition);
//
//                    // }
//                }
//                //dataAdapter2.notifyDataSetChanged();
//              //  dataAdapter2.notifyDataSetChanged();
//                System.out.println("hahahahahaha00  adadasd      ");
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//        System.out.println("hahahahaasdasdsadhaha00  aasdasdasddadasd      ");
//
//        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                itemCondition = parent.getItemAtPosition(position).toString();
//                System.out.println("hahahahahaha00        "+itemCondition);
//
//                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), "Selected: " + itemCondition, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinnerGroup = (Spinner) findViewById(R.id.spinnerGroup);
//        classgroup.add("groupx");
//        dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classgroup);
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerGroup.setAdapter(dataAdapter1);
//       databaseReference.child("Staff").child("12WAR1233").addValueEventListener(new ValueEventListener() {
//          @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> map = (Map) dataSnapshot.getValue();
//
//                String course = map.get("course").toString();
//                // String  classGroup = map.get("class").toString();
//                String[] separated = course.split(",");
//
//                for (int i = 0; i < separated.length; i++) {
//                    //  if(separated[i].equals("|"+fromStation)){
//                    //  from = i+1;
//                    //  fromPrice = Integer.parseInt(separatedPrice[i]);
//                    //   fromDur = Integer.parseInt(separatedDuration[i]);
//                    classgroup.add(separated[i]);
//                    System.out.println("hahahxccczxczxczczxc     " + separated[i]);
//                    System.out.println("hahahxccczxczxczczxc     " + classgroup);
//
//                    // }
//                }
//                //dataAdapter2.notifyDataSetChanged();
//                //  dataAdapter2.notifyDataSetChanged();
//                System.out.println("hahahahahaha00  adadasd      ");
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//        System.out.println("hahahahaasdasdsadhaha00  aasdasdasddadasd      ");
//
//        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                itemCondition = parent.getItemAtPosition(position).toString();
//                System.out.println("hahahahahaha00        "+itemCondition);
//
//                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), "Selected: " + itemCondition, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
//
//
//
//    }
//
//
////    @Override
////    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////        // On selecting a spinner item
////       itemCondition = parent.getItemAtPosition(position).toString();
////        System.out.println("hahahahahaha00        "+itemCondition);
////
////        // Showing selected spinner item
////        Toast.makeText(parent.getContext(), "Selected: " + itemCondition, Toast.LENGTH_SHORT).show();
////
////    }
////    public void onNothingSelected(AdapterView<?> arg0) {
////        // TODO Auto-generated method stub
////    }
//
//
//    @Override
//    public void onClick(View view) {
//        if(R.id.buttonPublish== view.getId()){
//
//       FirebaseDatabase database = FirebaseDatabase.getInstance();
//       DatabaseReference databaseReference = database.getReference().getRoot().child("Announcement").child(itemCondition);
//            Toast.makeText(this, "Selected: " + itemCondition, Toast.LENGTH_SHORT).show();
//
//            databaseReference.child("Title").setValue("Take leave");
//       databaseReference.child("Message").setValue("Dont go class today");
//        databaseReference.child("name").setValue("Ms. tan");
//
//        }
//
//    }







    @Override
    public void onBackPressed() {
        // your code.
        Intent intent2 = new Intent(AnnouncementActivity.this, AnnounceListLectActivity.class);
        startActivity(intent2);
        finish();

    }











}
