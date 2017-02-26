package com.example.winnie.fypmbassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.winnie.fypmbassignment.Crop.Constants;
import com.example.winnie.fypmbassignment.Crop.ImageCropActivity;
import com.example.winnie.fypmbassignment.Crop.PicModeSelectDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductAddActivity extends ActionBarActivity implements View.OnClickListener, PicModeSelectDialogFragment.IPicModeSelectListener{
    private EditText etMessageBox,texttitle,textPrice,textLocation;
    private Button  btn_cleartitle,btn_clearbox,btn_clearPrice,btn_clearlocation,buttonSell;
    ImageView imageadd1,imageadd2,imageadd3,imageminus1,imageminus2,imageminus3;
    Spinner spinnerCategory,spinnerCondition;
    String itemCategory,itemCondition ;
    public static final String TEMP_PHOTO_FILE_NAME = "userprofi.png";
    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;
    private File mFileTemp;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private boolean imagec = false,imagec1 = false;
     ImageView current ;
    List<String> ProductExistList = new ArrayList<String>();
    Uri downloadUri1,downloadUri2,downloadUri3;
    private String productlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        etMessageBox = (EditText)findViewById(R.id.etMessageBox);
        btn_clearbox = (Button)findViewById(R.id.btn_clearbox);

        texttitle = (EditText)findViewById(R.id.texttitle);
        btn_cleartitle = (Button)findViewById(R.id.btn_cleartitle);

        textPrice = (EditText)findViewById(R.id.textPrice);
        btn_clearPrice = (Button)findViewById(R.id.btn_clearPrice);

        textLocation = (EditText)findViewById(R.id.textLocation);
        btn_clearlocation = (Button)findViewById(R.id.btn_clearlocation);
        buttonSell = (Button)findViewById(R.id.buttonSell);

         imageadd1 = (ImageView)findViewById(R.id.imageadd1);
        imageadd2 = (ImageView)findViewById(R.id.imageadd2);
        imageadd3 = (ImageView)findViewById(R.id.imageadd3);

        imageminus1 = (ImageView)findViewById(R.id.imageminus1);
        imageminus2 = (ImageView)findViewById(R.id.imageminus2);
        imageminus3 = (ImageView)findViewById(R.id.imageminus3);

        etMessageBox.addTextChangedListener(textWatcher());
        texttitle.addTextChangedListener(textWatcher());
        textPrice.addTextChangedListener(textWatcher());
        textLocation.addTextChangedListener(textWatcher());

        btn_clearbox.setOnClickListener(this);
        btn_cleartitle.setOnClickListener(this);
        btn_clearPrice.setOnClickListener(this);
        btn_clearlocation.setOnClickListener(this);
        buttonSell.setOnClickListener(this);
        imageadd1.setOnClickListener(this);
        imageadd2.setOnClickListener(this);
        imageadd3.setOnClickListener(this);
        imageminus1.setOnClickListener(this);
        imageminus2.setOnClickListener(this);
        imageminus3.setOnClickListener(this);

        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerCondition = (Spinner) findViewById(R.id.spinnerCondition);



        List<String> categories = new ArrayList<String>();
        categories.add("Mensfashion");
        categories.add("Womensfashion");
        categories.add("Beauty");
        categories.add("HomeNLiving");
        categories.add("GadgetsNAccessories");
        categories.add("Books");
        categories.add("ArtNDesign");
        categories.add("CollectiblesNHobbies");
        categories.add("EverythingElse");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapter);

        List<String> condition = new ArrayList<String>();
        condition.add("Condition");
        condition.add("Preloved");
        condition.add("New");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, condition);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondition.setAdapter(dataAdapter2);

        mProgress = new ProgressDialog(this);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                itemCategory = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + itemCategory, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemCondition = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + itemCondition, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clearbox:
                etMessageBox.setText("");
                break;

            case R.id.btn_cleartitle:
                texttitle.setText("");
                break;

            case R.id.btn_clearPrice:
                textPrice.setText("");
                break;

            case R.id.btn_clearlocation:
                textLocation.setText("");
                break;
            case R.id.imageadd1:
                current = imageadd1;
                open();
                break;
            case R.id.imageminus1:
                imageadd1.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                imageadd2.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                imageadd3.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                imageadd2.setVisibility(View.GONE);
                imageminus2.setVisibility(View.GONE);
                break;
            case R.id.imageadd2:
                current = imageadd2;
                open();
                break;

            case R.id.imageminus2:
                imageadd2.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                imageadd3.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                imageadd3.setVisibility(View.GONE);
                imageminus3.setVisibility(View.GONE);
                break;
            case R.id.imageadd3:
                current = imageadd3;
                open();
                break;
            case R.id.imageminus3:
                imageadd3.setImageDrawable(getResources().getDrawable(R.drawable.ic_imageadd));
                break;
            case R.id.buttonSell:
                databaseAdd();

        }
    }

    private void databaseAdd(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().getRoot().child("Category").child(itemCategory);
        final String pushkey = databaseReference.push().getKey();

        databaseReference.child(pushkey).child("Item name").setValue(texttitle.getText().toString());
        databaseReference.child(pushkey).child("Student id").setValue("12AAD1212");
//        databaseReference.child(pushkey).child("Price").setValue(textPrice.getText().toString());
//        databaseReference.child(pushkey).child("Description").setValue(etMessageBox.getText().toString());
//        databaseReference.child(pushkey).child("Condition").setValue(itemCondition);
//        databaseReference.child(pushkey).child("Location").setValue(textLocation.getText().toString());
       databaseReference.child(pushkey).child("Image1").setValue(getImageViewImage1());
//        databaseReference.child(pushkey).child("Image2").setValue(getImageViewImage2());
//        databaseReference.child(pushkey).child("Image3").setValue(getImageViewImage3());

//        byte[] decodedString = Base64.decode(imageprofile, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        img.setImageBitmap(decodedByte);

        final DatabaseReference databaseReference2 = database.getReference().getRoot().child("student").child("12AAD1212");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map) dataSnapshot.getValue();

                productlist = map.get("productlist").toString();
//                String[] productlistsplit = productlist.split(",");
//
//                for(int j = 0 ;j < productlistsplit.length;j++){
//                    ProductExistList.add(productlistsplit[j]);
//                    System.out.println("aaaaaaaaaaaaaaaaaaaa      " + ProductExistList );
//
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference2.child("productlist").setValue(productlist+pushkey+",");

    }


    public String getImageViewImage1(){
        if(!imageadd1.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_imageadd).getConstantState())){

            Bitmap bitmap=((BitmapDrawable) imageadd1.getDrawable()).getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] imageByteArray=stream.toByteArray();

        String img_str = Base64.encodeToString(imageByteArray, 0);
        return img_str;
        }else {
            String img_str = "null";
            return img_str;
        }
    }
    public String getImageViewImage2(){
        if(!imageadd2.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_imageadd).getConstantState())){

         Bitmap bitmap=((BitmapDrawable) imageadd2.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] imageByteArray=stream.toByteArray();

        String img_str = Base64.encodeToString(imageByteArray, 0);




            return img_str;

        }else {
            String img_str = "null";
            return img_str;
        }
    }
    public String getImageViewImage3(){
        if(!imageadd3.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_imageadd).getConstantState())){

        Bitmap bitmap=((BitmapDrawable) imageadd3.getDrawable()).getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] imageByteArray=stream.toByteArray();

        String img_str = Base64.encodeToString(imageByteArray, 0);
        return img_str;
        }else {
            String img_str = "null";
            return img_str;
        }
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
            current.setImageBitmap(myBitmap);
            if(current.equals(imageadd1)){
                imageadd2.setVisibility(View.VISIBLE);
                imageminus2.setVisibility(View.VISIBLE);
            }else if(current.equals(imageadd2)){
                imageadd3.setVisibility(View.VISIBLE);
                imageminus3.setVisibility(View.VISIBLE);

        }
        }
    }


    public void open(){
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













    private TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etMessageBox.getText().toString().equals("")) { //if edittext include text
                    btn_clearbox.setVisibility(View.VISIBLE);
                } else { //not include text
                    btn_clearbox.setVisibility(View.GONE);
                 //   textView.setText("Edittext cleared!");
                }

                if (!texttitle.getText().toString().equals("")) { //if edittext include text
                    btn_cleartitle.setVisibility(View.VISIBLE);
                } else { //not include text
                    btn_cleartitle.setVisibility(View.GONE);
                }

                if (!textPrice.getText().toString().equals("")) { //if edittext include text
                    btn_clearPrice.setVisibility(View.VISIBLE);
                } else { //not include text
                    btn_clearPrice.setVisibility(View.GONE);
                }

                if (!textLocation.getText().toString().equals("")) { //if edittext include text
                    btn_clearlocation.setVisibility(View.VISIBLE);
                } else { //not include text
                    btn_clearlocation.setVisibility(View.GONE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}
