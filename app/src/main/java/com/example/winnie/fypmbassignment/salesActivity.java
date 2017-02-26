package com.example.winnie.fypmbassignment;


        import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class salesActivity extends Activity{

    ListView list;
    String[] itemname ={
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    Integer[] imgid={
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
            R.drawable.bankbuilding,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

     //   CustomListSalesAdapter adapter=new CustomListSalesAdapter(this, itemname, imgid);
//        list=(ListView)findViewById(R.id.list);
//        list.setAdapter(adapter);
//
//        list.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String Slecteditem= itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }





}