package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_PICTURE = 2;
    private Button button;
    private ImageView imageView;
    private StorageReference mStorage;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        mStorage = FirebaseStorage.getInstance().getReference();
//        mProgress = new ProgressDialog(this);
//        button = (Button) findViewById(R.id.button);
//        imageView = (ImageView) findViewById(R.id.imageView);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startDialog();
//
//            }
//        });
//    }
//
//    private void startDialog() {
//        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(ProfileActivity.this);
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
//                            startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
//
//                        } catch (ActivityNotFoundException e) {
//// Do nothing for now
//                            Toast.makeText(ProfileActivity.this, "Your phone not support.", Toast.LENGTH_LONG).show();
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
//                    Toast.makeText(ProfileActivity.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//        myAlertDialog.show();
//    }
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
//                            Uri downloadUri = taskSnapshot.getDownloadUrl();
//                            Picasso.with(ProfileActivity.this).load(downloadUri).fit().centerCrop().into(imageView);
//                            Toast.makeText(ProfileActivity.this, "Upload done....", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                } catch (NullPointerException e) {
//// Do nothing for now
//                    mProgress.dismiss();
//                    Toast.makeText(ProfileActivity.this, "Your phone not support.", Toast.LENGTH_LONG).show();
//
//                } catch (Exception e) {
//// Do nothing for now
//                    mProgress.dismiss();
//                    Toast.makeText(ProfileActivity.this, "Your phone not support.", Toast.LENGTH_LONG).show();
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
//                        Uri downloadUri = taskSnapshot.getDownloadUrl();
//                        Picasso.with(ProfileActivity.this).load(downloadUri).fit().centerCrop().into(imageView);
//                        Toast.makeText(ProfileActivity.this, "Upload done....", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
//
//
//        }

    }
}
