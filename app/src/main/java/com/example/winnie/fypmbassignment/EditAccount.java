package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.winnie.fypmbassignment.Crop.Constants;
import com.example.winnie.fypmbassignment.Crop.ImageCropActivity;
import com.example.winnie.fypmbassignment.Crop.PicModeSelectDialogFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class EditAccount extends AppCompatActivity implements PicModeSelectDialogFragment.IPicModeSelectListener{


    ImageView avatar;
    UserLocalStore userLocalStore;
    User user;

    private boolean connected;
    EditText editTextEmail;
    EditText editTextFirstName, editTextLastName;
    Button btnMale, btnFemale;
    ImageButton clearFirstName, clearLastName;
    TextView firstNameTextView, lastNameTextView, genderTextView;
    String gender = "";


    private File      mFileTemp;

    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);




        avatar = (ImageView)findViewById(R.id.avatar);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        }
        else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }


        refresh();


    }

    public void refresh(){
//        editTextEmail.setText(user.getEmail().toLowerCase());
//        editTextFirstName.setText(user.getFirstName().toUpperCase());
//        editTextLastName.setText(user.getLastName().toUpperCase());
//        gender = user.getGender();
//        if(gender.equals("Male")){
//            selectMale();
//        }else if(gender.equals("Female")){
//            selectFemale();
//        }
     //   byte[] decodedString = Base64.decode(user.getImage(), Base64.DEFAULT);
     //   Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
     //   avatar.setImageBitmap(decodedByte);
    }



    public String getImageViewImage(){
        Bitmap bitmap=((BitmapDrawable) avatar.getDrawable()).getBitmap();

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
            avatar.setImageBitmap(myBitmap);
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
