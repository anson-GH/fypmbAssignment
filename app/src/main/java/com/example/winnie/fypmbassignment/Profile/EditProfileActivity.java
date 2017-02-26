package com.example.winnie.fypmbassignment.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.winnie.fypmbassignment.CircleImageView;
import com.example.winnie.fypmbassignment.Crop.Constants;
import com.example.winnie.fypmbassignment.Crop.ImageCropActivity;
import com.example.winnie.fypmbassignment.Crop.PicModeSelectDialogFragment;
import com.example.winnie.fypmbassignment.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;


public class EditProfileActivity extends AppCompatActivity implements PicModeSelectDialogFragment.IPicModeSelectListener {
    EditText etContact, etCity, etAddress,etEmail;
    Button buttonSubmit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_PICTURE = 2;
    private Button button;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    ImageView img;
    private File mFileTemp;

    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        ColorDialog dialog = new ColorDialog(this);
//        dialog.setTitle("operation");
//        dialog.setContentText("loading..");
//        dialog.setContentImage(getResources().getDrawable(R.mipmap.ic_help));
//        dialog.setPositiveListener("delete", new ColorDialog.OnPositiveListener() {
//            @Override
//            public void onClick(ColorDialog dialog) {
//                Toast.makeText(EditProfileActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        })
//                .setNegativeListener("cancel", new ColorDialog.OnNegativeListener() {
//                    @Override
//                    public void onClick(ColorDialog dialog) {
//                        Toast.makeText(EditProfileActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                }).show();


        etEmail = (EditText) findViewById(R.id.etEmail);
        etContact = (EditText) findViewById(R.id.etContact);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etCity = (EditText) findViewById(R.id.etCity);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        img = (CircleImageView) findViewById(R.id.image);
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().getRoot().child("student").child("12AA102");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map) dataSnapshot.getValue();
              String contact = map.get("contact").toString();
                String address = map.get("address").toString();
                String city = map.get("city").toString();
                String emailaddress = map.get("emailaddress").toString();
                String imageprofile = map.get("imageprofile").toString();
                etEmail.setText(emailaddress);
                        etCity.setText(city);
                etAddress.setText(address);
                        etContact.setText(contact);

                byte[] decodedString = Base64.decode(imageprofile, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                img.setImageBitmap(decodedByte);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_menu_done:
                save();
                Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void save() {

       databaseReference.child("contact").setValue(etContact.getText().toString());
        databaseReference.child("address").setValue(etAddress.getText().toString());
        databaseReference.child("city").setValue(etCity.getText().toString());
       databaseReference.child("emailaddress").setValue(etEmail.getText().toString());
        databaseReference.child("imageprofile").setValue(getImageViewImage());

//        new PromptDialog(this)
//                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
//                .setAnimationEnable(true).setTitleText("success").setContentText("complete")
//              //  .setTitleText(getString(R.string.success))
//              //  .setContentText(getString(R.string.text))
//                .setPositiveListener("ok", new PromptDialog.OnPositiveListener() {
//                    @Override
//                    public void onClick(PromptDialog dialog) {
//                        dialog.dismiss();
//                    }
//                }).show();

    }
    public String getImageViewImage(){
        Bitmap bitmap=((BitmapDrawable) img.getDrawable()).getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] imageByteArray=stream.toByteArray();

        String img_str = Base64.encodeToString(imageByteArray, 0);
        return img_str;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == REQUEST_CODE_UPDATE_PIC) {
            if (resultCode == RESULT_OK) {
                String imagePath = result.getStringExtra(Constants.IntentExtras.IMAGE_PATH);
                showCroppedImage(imagePath);
            } else if (resultCode == RESULT_CANCELED) {

            } else {
                String errorMsg = result.getStringExtra(ImageCropActivity.ERROR_MSG);
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
    private void showCroppedImage(String mImagePath) {
        if (mImagePath != null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(mImagePath);
            img.setImageBitmap(myBitmap);
        }
    }


    public void open(View v){
        showAddProfilePicDialog();
    }
    private void showAddProfilePicDialog() {
        PicModeSelectDialogFragment dialogFragment = new PicModeSelectDialogFragment();
        dialogFragment.setiPicModeSelectListener(this);
        dialogFragment.show(getFragmentManager(), "picModeSelector");
    }

    private void actionProfilePic(String action) {
        Intent intent = new Intent(this, ImageCropActivity.class);
        intent.putExtra("ACTION", action);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_PIC);
    }


    @Override
    public void onPicModeSelected(String mode) {
        String action = mode.equalsIgnoreCase(Constants.PicModes.CAMERA) ? Constants.IntentExtras.ACTION_CAMERA : Constants.IntentExtras.ACTION_GALLERY;
        actionProfilePic(action);
    }


}
