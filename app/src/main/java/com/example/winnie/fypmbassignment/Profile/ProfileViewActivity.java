package com.example.winnie.fypmbassignment.Profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.winnie.fypmbassignment.CircleImageView;
import com.example.winnie.fypmbassignment.R;
import com.example.winnie.fypmbassignment.UserLocalStore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileViewActivity extends AppCompatActivity {
    TextView count;
    TextView etFaculty,etName , etProgramme, etRedemption,  etContact, etAddress, etCity, etGender, etIC;
    String name,gender,city,nric;
    UserLocalStore userLocalStore;

    CircleImageView imageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        etName = (TextView) findViewById(R.id.etName);
        etFaculty = (TextView) findViewById(R.id.etFaculty);
        etProgramme = (TextView) findViewById(R.id.etProgramme);
        etRedemption = (TextView) findViewById(R.id.etRedemption);
        etContact = (TextView) findViewById(R.id.etContact);
        etAddress = (TextView) findViewById(R.id.etAddress);
        etCity = (TextView) findViewById(R.id.etCity);
        etGender = (TextView) findViewById(R.id.etGender);
        etIC = (TextView) findViewById(R.id.etIC);
        userLocalStore = new UserLocalStore(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot();
        databaseReference.child("account").child("12AAD102").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
          //      for (DataSnapshot keys : snapshot.getChildren()) {
          Map<String, Object> map = (Map) dataSnapshot.getValue();


                name = map.get("name").toString();
//                                    String faculty = map.get("faculty").toString();
//                    String programme = map.get("programme").toString();
//                    String redemption = map.get("redemption").toString();
//                    String contact = map.get("contact").toString();
//                    String address = map.get("address").toString();
                    city = map.get("image").toString();
                  gender = map.get("gender").toString();
                        nric = map.get("ic").toString();

                etName.setText(name);
               etCity.setText(city);
//                    etFaculty.setText(faculty);
//                    etProgramme.setText(programme);
//                    etRedemption.setText(redemption);
//                    etContact.setText(contact);
//                    etAddress.setText(address);
                   etCity.setText(city);
              etGender.setText(gender);
        etIC.setText(nric);

        //    }
      }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    //    etName.setText(name);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
//        case R.id.add:
//             count = (TextView) findViewById(R.id.textView);
//            count.setText("Add is clicked");
//            return(true);
//        case R.id.reset:
//            count=(TextView)findViewById(R.id.textView);
//            count.setText("Nothing is selected");
//            return(true);
        case R.id.editprofile:
           // Toast.makeText(this, "hehere", Toast.LENGTH_LONG).show();
          //  startActivity(new Intent(this, EditProfile.class));
            return(true);
        case R.id.logout:
           // finish();
        //    userLocalStore.clearUserData();
        //    userLocalStore.setUserLoggedIn(false);
         //   startActivity(new Intent(this, LoginActivity.class));
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }

}
