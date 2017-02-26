package com.example.winnie.fypmbassignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomListFrenAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<FriendClass> friendClasslist = null;
    private ArrayList<FriendClass> arraylist;

    public CustomListFrenAdapter(Context context, List<FriendClass> friendClasslist) {
        mContext = context;
        this.friendClasslist = friendClasslist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FriendClass>();
        this.arraylist.addAll(friendClasslist);
    }

    public class ViewHolder {
        TextView txtPosition;
        ImageView ivImage;
        TextView txtName;
    }

    @Override
    public int getCount() {
        return friendClasslist.size();
    }

    @Override
    public FriendClass getItem(int position) {
        return friendClasslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listfriend, null);
            // Locate the TextViews in listview_item.xml
            holder.txtPosition = (TextView) view.findViewById(R.id.txtPosition);
            holder.txtName = (TextView) view.findViewById(R.id.txtName);
            // Locate the ImageView in listview_item.xml
          //  holder.flag = (ImageView) view.findViewById(R.id.flag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.txtName .setText(friendClasslist.get(position).getUsername());
        holder.txtPosition.setText(friendClasslist.get(position).getUserposition());
        // Set the results into ImageView
      //  holder.ivImage.setImageResource(friendClasslist.get(position).getStudentImage());

      //   Listen for ListView Item Click
//        view.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(mContext, SingleItemView.class);
//                intent.putExtra("nameF",(friendClasslist.get(position).getStudentname()));
//                intent.putExtra("cityF",(friendClasslist.get(position).getStudentcity()));
//           //     intent.putExtra("imageF",(friendClasslist.get(position).getStudentImage()));
//
//           //     intent.putExtra("flag",(worldpopulationlist.get(position).getFlag()));
//                // Start SingleItemView Class
//                mContext.startActivity(intent);
//            }
//        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        friendClasslist.clear();
        if (charText.length() == 0) {
     //       friendClasslist.addAll(arraylist);
        } else {
            for (FriendClass wp : arraylist) {
                if (wp.getUsername().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    friendClasslist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
