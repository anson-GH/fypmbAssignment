package com.example.winnie.fypmbassignment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProductActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
 //   HashMap<String, String> HashMapForURL;

    HashMap<String, Object> HashMapForLocalRes ;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        //Call this method if you want to add images from URL .
      //  AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .


        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();
        imageView = (ImageView) findViewById(R.id.imageView);

//        for (String name : HashMapForLocalRes.keySet()) {
//
//            TextSliderView textSliderView = new TextSliderView(ProductActivity.this);
//
//            textSliderView
//                    .description(name)
//                    .image((File) HashMapForLocalRes.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//
//            textSliderView.bundle(new Bundle());
//
//            textSliderView.getBundle()
//                    .putString("extra", name);
//
//            sliderLayout.addSlider(textSliderView);
//        }
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
//
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//
//        sliderLayout.setDuration(3000);
//
//        sliderLayout.addOnPageChangeListener(ProductActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().getRoot().child("Category").child("Condition");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map) dataSnapshot.getValue();
                String image1 = map.get("Image1").toString();
            //    String imageprofile = map.get("Image2").toString();


                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
//                byte[] decodedString2 = Base64.decode(imageprofile, Base64.DEFAULT);
//                Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString.length);
//                imageView.setImageBitmap(decodedByte);
                AddImageUrlFormLocalRes();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {

    //    sliderLayout.stopAutoCycle();

    //    super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

   //     Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

     //   Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline() {
//
     //   HashMapForURL = new HashMap<String, String>();
//
        //   HashMapForURL.put("CupCake", "https://firebasestorage.googleapis.com/v0/b/train-24c39.appspot.com/o/16216329_10210438063180978_1389310486_n.jpg?alt=media&token=c91a2c6c-3784-4b44-88c3-87264043bda7");
        //  HashMapForURL.put("Donut", "https://firebasestorage.googleapis.com/v0/b/ansonproject-4a171.appspot.com/o/download.jpg?alt=media&token=7cd61f37-b643-46e9-b330-79ed28b7c8cd");
    //    HashMapForURL.put("Eclair", "https://firebasestorage.googleapis.com/v0/b/ansonproject-4a171.appspot.com/o/cupcake.png?alt=media&token=5219b933-42c9-4711-9501-e23ac16d4b20");
     //   HashMapForURL.put("Froyo", "http://androidblog.esy.es/images/froyo-4.png");
        //  HashMapForURL.put("GingerBread", "https://firebasestorage.googleapis.com/v0/b/ansonproject-4a171.appspot.com/o/background.png?alt=media&token=61cb1d02-5709-4dbf-a664-5316ef9b7a11");


    }

    public void AddImageUrlFormLocalRes() {





      //    HashMapForLocalRes = new HashMap<String, Object>();

      //   HashMapForLocalRes.put("CupCake", R.drawable.donut);
       //    HashMapForLocalRes.put("Donut", imageView);
      //    HashMapForLocalRes.put("Eclair", R.drawable.eclair);
      //   HashMapForLocalRes.put("Froyo", R.drawable.froyo);
      //    HashMapForLocalRes.put("GingerBread", R.drawable.gingerbread);

    }
}
