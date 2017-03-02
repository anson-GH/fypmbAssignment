package com.example.winnie.fypmbassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Winnie on 25/2/2017.
 */

public class CustomListChatAdapter  extends BaseAdapter {
    private Context mContext;
  //  ChatClass[] chatClass;
    private List<ChatClass> chatClass = null;

    public CustomListChatAdapter(Context context, List<ChatClass> chatClass)   {
        // TODO Auto-generated constructor stub

        mContext = context;
        this.chatClass = chatClass;
        // this.imgid=imgid;
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return chatClass.size();
    }



    public String getItem(int arg0) {
        // TODO Auto-generated method stub
        return chatClass.get(arg0).getChatname();
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        rowView = inflater.inflate(R.layout.chatlist , parent, false);

        TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
        //  ImageView ivImage = (ImageView) rowView.findViewById(R.id.ivImage);
         TextView txtMessage = (TextView) rowView.findViewById(R.id.txtMessage);
        TextView txtTime = (TextView) rowView.findViewById(R.id.txtTime);

//        byte[] decodedString = Base64.decode(saleClass[position].getImage(), Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        ivImage.setImageBitmap(decodedByte);

        txtName.setText(chatClass.get(position).getChatname());
        txtMessage.setText(chatClass.get(position).ChatUser+":\n"+ chatClass.get(position).getChatMessage());

//        long time =  // The Twitter post time stamp
//        long now = System.currentTimeMillis();
//        CharSequence relativeTimeStr = DateUtils.getRelativeTimeSpanString(time,
//                now, DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE);
        String vv;
        String DT =chatClass.get(position).getChatTimeDate();
        if(DT.equals("")){

            txtTime.setText("");
        }else{
            long dv = Long.valueOf(DT)*1000;// its need to be in milisecond
            Date df = new java.util.Date(dv);
             vv = new SimpleDateFormat("hh:mma MMM, dd").format(df);
            txtTime.setText(vv);
        }



        return rowView;
    }



}
