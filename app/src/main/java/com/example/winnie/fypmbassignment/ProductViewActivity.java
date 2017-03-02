        package com.example.winnie.fypmbassignment;

        import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
        import android.text.format.DateUtils;
        import android.util.TypedValue;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

        import java.text.DecimalFormat;
        import java.util.ArrayList;

        public class ProductViewActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener {

            SliderLayout sliderLayout;
            TextView etItemName,etCondition,etTime,etPrice,etDescription;
            String KeyF;
            String nameF;
            String priceF;
            String descriptionF;
            String conditionF;
            String categoryF;
            String image1F;
            String image2F;
            String image3F;
            String locationF;
            String timeStampF;
            String statuSF;
            String studIDF;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_product_view);

                Intent i = getIntent();
                KeyF = i.getStringExtra("itemKeyPass");
                nameF = i.getStringExtra("itemNamePass");
                priceF = i.getStringExtra("itemPricePass");
                descriptionF = i.getStringExtra("itemDescriptionPass");
                conditionF = i.getStringExtra("itemConditionPass");
                categoryF = i.getStringExtra("itemCategoryPass");
                image1F = i.getStringExtra("itemImage1Pass");
                image2F = i.getStringExtra("itemImage2Pass");
                image3F = i.getStringExtra("itemImage3Pass");
                locationF = i.getStringExtra("itemLocationPass");
                timeStampF = i.getStringExtra("itemTimestampPass");
                statuSF = i.getStringExtra("itemStatusPass");
                studIDF = i.getStringExtra("itemStudIDPass");

                etPrice = (TextView) findViewById(R.id.etPrice);
                etItemName = (TextView) findViewById(R.id.etItemName);
                etCondition = (TextView) findViewById(R.id.etCondition);
                etDescription = (TextView) findViewById(R.id.etDescription);
                etTime = (TextView) findViewById(R.id.etTime);

                etItemName.setText(nameF);
                etCondition.setText(conditionF);
                etDescription.setText(descriptionF);
                String formattedPrice = new DecimalFormat("##,##0.00").format(Double.parseDouble(priceF));
                etPrice.setText(formattedPrice);

                long dv = Long.valueOf(timeStampF)*1000;// its need to be in milisecond
                long now = System.currentTimeMillis();

                CharSequence ago = DateUtils.getRelativeTimeSpanString(dv, now, DateUtils.SECOND_IN_MILLIS);
                etTime.setText(ago);
                //Call this method if you want to add images from URL .
               AddImagesUrlOnline();

            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                //  Log.d("Slider Demo", "Page Changed: " + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            public void AddImagesUrlOnline() {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference().getRoot();
                databaseReference.child("Category").child("Mensfashion").child("-KdZOwz2YdK-JlppuhAD").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    //    Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                     //   System.out.println("1   " + it);

                     //   while (it.iterator().hasNext()) {
                       //     DataSnapshot ds = it.iterator().next();
                         //   System.out.println("2   " + ds.getKey());

                         //   Map<String, Object> map = (Map) dataSnapshot.getValue();

                      //  System.out.println("1.    "+dataSnapshot.child("Image1").getValue().toString());
                            ArrayList urlimage = new ArrayList();

                            for (int i = 0; i < 1; i++) {
                                if (!dataSnapshot.child("Image" + (i+1)).getValue().toString().equals("null"))
                                    urlimage.add(dataSnapshot.child("Image" +(1+i)).getValue().toString());

                           }


                            ViewPager vp = (ViewPager) findViewById(R.id.view);
                            ImageAdapter adap = new ImageAdapter(ProductViewActivity.this, urlimage, "");

                            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
                            tabLayout.setupWithViewPager(vp, true);
                            final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -8, getResources().getDisplayMetrics());
                            vp.setPageMargin(pageMargin);
                            vp.setAdapter(adap);
                     //   }
                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        }
