package com.example.winnie.fypmbassignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class SalesCategoryActivity extends AppCompatActivity {
    GridView grid;
    String[] web = {
            "Men's fashion",
            "Women's fashion",
            "Home & Living",
            "Beauty",
            "Books",
            "Art & Design",
            "Gadgets & Accessories",
            "Collectibles & Hobbies",
            "Everything else"

    } ;
    int[] imageId = {
            R.drawable.men,
            R.drawable.wome,
            R.drawable.homr,
            R.drawable.beauty,
            R.drawable.book,
            R.drawable.art,
            R.drawable.gad,
            R.drawable.hobb,
            R.drawable.others
    };
    Intent intent;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_category);
        if (isNetworkAvailable(getApplicationContext())) {
        CustomGridCategoryAdapter adapter = new CustomGridCategoryAdapter(SalesCategoryActivity.this, web, imageId);
        grid = (GridView) findViewById(R.id.gridCategory);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(MainMenuActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
        String gridchosen = null;
                intent = new Intent(SalesCategoryActivity.this, SalesListActivity.class);

                if (position == 0) {
                    gridchosen = "Menfashion";
                }
                if (position == 1) {
                    gridchosen = "Womenfashion";
                }

                intent.putExtra("salesclick", gridchosen);
                startActivity(intent);
            }
        });

                 } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing the App")
                    .setMessage("No Internet Connection,check your settings")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    }).show();
        }
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
