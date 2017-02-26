package com.example.winnie.fypmbassignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListSalesAdapter extends BaseAdapter {

    private Context mContext;
    SalesClass[] saleClass;

   // private final Integer[] imgid;

    public CustomListSalesAdapter(Context context, SalesClass[] saleClass)   {
        // TODO Auto-generated constructor stub

        mContext = context;
        this.saleClass = saleClass;
       // this.imgid=imgid;
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return saleClass.length;
    }



    public String getItem(int arg0) {
        // TODO Auto-generated method stub
        return saleClass[arg0].getName();
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        rowView = inflater.inflate(R.layout.mylist, parent, false);

        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
     //  ImageView ivImage = (ImageView) rowView.findViewById(R.id.ivImage);
      //  TextView extratxt = (TextView) rowView.findViewById(R.id.textPriceList);

//        byte[] decodedString = Base64.decode(saleClass[position].getImage(), Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        ivImage.setImageBitmap(decodedByte);

        tvName.setText(saleClass[position].getName());
     //   extratxt.setText(saleClass[position].getImage());


        return rowView;
    }

}