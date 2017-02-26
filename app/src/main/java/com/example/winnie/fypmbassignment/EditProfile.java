package com.example.winnie.fypmbassignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class EditProfile extends Activity {
    EditText etContact, etCity, etAddress;
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
    Uri downloadUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
//
//        etContact = (EditText) findViewById(R.id.etContact);
//        etAddress = (EditText) findViewById(R.id.etAddress);
//        etCity = (EditText) findViewById(R.id.etCity);
//        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
//
//         img = (CircleImageView) findViewById(R.id.image);
//      //  img.setImageResource(R.drawable.m_tarc);
//       // img = (ImageView) findViewById(R.id.image);
//
//        mStorage = FirebaseStorage.getInstance().getReference();
//        mProgress = new ProgressDialog(this);
//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startDialog();
//
//            }
//        });
//    }
//
//    public void save(View view) {
//        // https://www.youtube.com/watch?v=nIaNdxXBKFY add data
//        databaseReference.child("name").setValue(etContact.getText().toString());
//        databaseReference.child("city").setValue(etAddress.getText().toString());
//        databaseReference.child("something").setValue(etCity.getText().toString());
//        databaseReference.child("download").setValue(downloadUri.toString());
//
//
//    }
//
//
//
//
//
//    private void startDialog() {
//        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(EditProfile.this);
//        myAlertDialog.setTitle("Upload Pictures Option");
//        myAlertDialog.setMessage("How do you want to set your picture?");
//
//        myAlertDialog.setPositiveButton("Gallery",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Intent pictureActionIntent = null;
//
//                        pictureActionIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        try {
//
//
//                            startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
//
//                        } catch (ActivityNotFoundException e) {
//// Do nothing for now
//                            Toast.makeText(EditProfile.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                });
//
//        myAlertDialog.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                try {
//                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
//                } catch (ActivityNotFoundException e) {
//// Do nothing for now
//                    Toast.makeText(EditProfile.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//        myAlertDialog.show();
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//            if (data.getParcelableExtra("data") != null) {
//
//                try {
//                    mProgress.setMessage("Uploading message");
//                    mProgress.show();
//                    Uri uri = data.getData();
//
//                    StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
//
//
//                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            mProgress.dismiss();
//                             downloadUri = taskSnapshot.getDownloadUrl();
//                            Picasso.with(EditProfile.this).load(downloadUri).fit().centerCrop().into(img);
//                            Toast.makeText(EditProfile.this, "Upload done....", Toast.LENGTH_LONG).show();
//
//                        }
//                    });
//                } catch (NullPointerException e) {
//// Do nothing for now
//                    mProgress.dismiss();
//                    Toast.makeText(EditProfile.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                } catch (Exception e) {
//// Do nothing for now
//                    mProgress.dismiss();
//                    Toast.makeText(EditProfile.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        }
//        if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
//            if (data != null) {
//                mProgress.setMessage("Uploading message");
//                mProgress.show();
//                Uri uri = data.getData();
//
//                StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
//
//                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        mProgress.dismiss();
//                         downloadUri = taskSnapshot.getDownloadUrl();
//                        Picasso.with(EditProfile.this).load(downloadUri).fit().centerCrop().into(img);
//                        Toast.makeText(EditProfile.this, "Upload done....", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
//
//
//        }

    }
}
