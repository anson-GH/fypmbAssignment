package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FriendProfileViewActivity extends AppCompatActivity {


    // Put your images from draweble folder
  //  private int[] Image = {R.drawable.ic_people,R.drawable.ic_people,R.drawable.ic_people,R.drawable.ic_people,R.drawable.ic_people,R.drawable.ic_people};
    TextView txtName,txtCity,btaddfriend,btcancelfriend;
    TextView txtPosition;
    ImageView imgF;
    String nameF;
    String PositionF;
    String idF;
    String imageF,postkey;
    public FirebaseDatabase database;
    ProgressDialog progressdialog;
    public DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4,databaseReference5,databaseReference6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        Context context;
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.pro2);

        Intent i = getIntent();
        nameF = i.getStringExtra("nameF");
        PositionF = i.getStringExtra("PositionF");
        idF = i.getStringExtra("idF");

        txtName = (TextView) findViewById(R.id.txtName);
        txtPosition = (TextView) findViewById(R.id.txtPosition);
        txtCity  = (TextView) findViewById(R.id.txtCity);
        btaddfriend = (TextView) findViewById(R.id.btaddfriend);
        btcancelfriend = (TextView) findViewById(R.id.btcancelfriend);

//         progressdialog = new ProgressDialog(FriendProfileViewActivity.this);
//        progressdialog.setMessage("Please Wait....");
//        progressdialog.show();


        txtName.setText(nameF);
        txtPosition.setText(PositionF);

        ImageView imgv = (ImageView) findViewById(R.id.banar1);

        System.out.println("position   "+ PositionF);

        //  Bitmap bitmap = StringToBitMap(imgv);
//        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
//        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        Canvas c = new Canvas(circleBitmap);
//        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
//
//
//        imgv.setImageBitmap(circleBitmap);


          database = FirebaseDatabase.getInstance();
          databaseReference = database.getReference().getRoot().child(PositionF).child(idF);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtCity.setText(dataSnapshot.child("city").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        refresh();




        btcancelfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference5.child("12AAD1212").child("friendlist").child(postkey).removeValue();
                databaseReference3.child(idF).child("friendlist").child(postkey).removeValue();
                databaseReference4.child(postkey).removeValue();
                databaseReference6.child(postkey).removeValue();

                refresh();
          //      finish();
//                btaddfriend.setVisibility(View.VISIBLE);
//                btcancelfriend.setVisibility(View.GONE);
            }
        });
        btaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String puskey = databaseReference2.child("friendlist").push().getKey();
//                databaseReference6.child(puskey).child("Message").setValue("");
//                databaseReference6.child(puskey).child("TIMESTAMP").setValue("");

                HashMap<String, Object> result = new HashMap<>();
                result.put("id", idF);
                result.put("name", nameF);
                result.put("position", PositionF);
                databaseReference5.child("12AAD1212").child("friendlist").child(puskey).updateChildren(result);

                HashMap<String, Object> result2 = new HashMap<>();
                result2.put("Message", "");
                result2.put("TIMESTAMP", "");
                result2.put("name", "");
                databaseReference6.child(puskey).updateChildren(result2);

                databaseReference3.child(idF).child("friendlist").child(puskey).child("id").setValue("12AAD1212");
                databaseReference3.child(idF).child("friendlist").child(puskey).child("name").setValue("john");
                databaseReference3.child(idF).child("friendlist").child(puskey).child("position").setValue("student");

                databaseReference4.child(puskey).setValue("");

                System.out.println(" add friend");
                btcancelfriend.setVisibility(View.VISIBLE);
                btaddfriend.setVisibility(View.GONE);

            }
        });
    }

    public void refresh(){
        databaseReference3 = database.getReference().getRoot().child(PositionF);
        databaseReference4 = database.getReference().getRoot().child("chat");
        databaseReference5 = database.getReference().getRoot().child("student");
        databaseReference6 = database.getReference().getRoot().child("chatlatest");

          databaseReference2 = database.getReference().getRoot();
        Query query = databaseReference2.child("student").child("12AAD1212").child("friendlist").orderByChild("id").equalTo(idF);


              query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                   System.out.println("1.   " + dataSnapshot.getValue().toString());
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        System.out.println("2.   " + postSnapshot.child("id").getValue().toString());

                        postkey =postSnapshot.getKey().toString();
                    }
                    System.out.println("postkey   "+ postkey);


                        btcancelfriend.setVisibility(View.VISIBLE);
                        btaddfriend.setVisibility(View.GONE);
                }else
                {
                        btaddfriend.setVisibility(View.VISIBLE);
                        btcancelfriend.setVisibility(View.GONE);
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
