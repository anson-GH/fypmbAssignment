package com.example.winnie.fypmbassignment;

/**
 * Created by bnaitali on 23/08/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bnaitali on 01/09/2015.
 */
public class ImageAdapter extends PagerAdapter {
    Context context;
  //  private String imageUrls[] ;
    private ArrayList<String> imageUrls = new ArrayList();

    private String names ;

    ImageAdapter(Context context, ArrayList<String> imageUrls, String names){
        this.context=context;
        this.imageUrls=imageUrls;
        this.names=names;

    }
 //   @Override
   public int getCount() {
        return imageUrls.size();
   }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        int padding = 0;

        imageView.setPadding(padding, padding, padding, padding);
      //  imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int i = ((ViewPager) container).getCurrentItem();
        Toast.makeText(context, "" + names, Toast.LENGTH_SHORT).show();
   //     imageView.setImageResource(GalImages[position]);
        byte[] decodedString = Base64.decode(imageUrls.get(position), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
//        Picasso
//                .with(context)
//                .load(imageUrls[position])
//                .fit() // will explain later
//                .into((imageView));

        ((ViewPager) container).addView(imageView, 0);

        // position = position -1;



        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}