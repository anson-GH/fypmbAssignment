        package com.example.winnie.fypmbassignment;

        import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

        public class ProductViewActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener {

            SliderLayout sliderLayout;
            TextView etItemName;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_product_view);




                etItemName = (TextView) findViewById(R.id.etItemName);

                String s = getIntent().getStringExtra("EXTRA_SESSION_ID");

                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

                etItemName.setText(s.toString());

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