package com.example.winnie.fypmbassignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomListAnnounceAdapter extends BaseAdapter {

    private Context mContext;
    AnnounceClass[] annClass;



    public CustomListAnnounceAdapter(Context context,AnnounceClass[] annClass)   {
        mContext = context;
        this.annClass = annClass;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return annClass.length;
    }



    public String getItem(int arg0) {
        // TODO Auto-generated method stub
        return annClass[arg0].getTitle();
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater =  LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.list_notice, parent, false);



        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtAnnTitle);
        TextView txtCourse = (TextView) convertView.findViewById(R.id.txtAnnCourse);
        TextView txtTime = (TextView) convertView.findViewById(R.id.txttime);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);


        txtTitle.setText(annClass[position].getTitle());
        txtCourse.setText(annClass[position].getCourse());
        txtDate.setText(annClass[position].getDateD());
        txtTime.setText(annClass[position].getTimeD());


        return convertView;
    }

}