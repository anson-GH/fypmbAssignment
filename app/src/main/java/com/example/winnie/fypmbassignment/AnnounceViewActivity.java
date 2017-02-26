package com.example.winnie.fypmbassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AnnounceViewActivity extends AppCompatActivity {
    TextView tvTitle,tvCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_view);
        String TitlePass = getIntent().getStringExtra("TitlePass");
        String CoursePass = getIntent().getStringExtra("CoursePass");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvCourse = (TextView) findViewById(R.id.tvCourse);

        tvTitle.setText(TitlePass);
        tvCourse.setText(CoursePass);

    }
}
