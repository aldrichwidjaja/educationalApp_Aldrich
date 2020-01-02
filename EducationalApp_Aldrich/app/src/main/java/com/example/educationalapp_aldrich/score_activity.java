package com.example.educationalapp_aldrich;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class score_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_activity);
        TextView tv = (TextView) findViewById(R.id.score);
        int score = getIntent().getIntExtra("score", 0);
        tv.setText("Current Score : " + score);
    }
}
