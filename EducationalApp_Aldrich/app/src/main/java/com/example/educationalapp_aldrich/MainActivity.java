package com.example.educationalapp_aldrich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button play;
    Button leaderboard;
    Button setting;
    MediaPlayer ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //START MEDIA PLAYER FOR BACKGROUND MUSIC
        ring = MediaPlayer.create(getApplicationContext(), R.raw.awesomeness);
        ring.setLooping(true);
        ring.start();


        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, game_activity.class);
                startActivity(i);
            }
        });

        leaderboard = (Button) findViewById(R.id.leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Leaderboards.class);
                startActivity(i);
            }
        });

        setting = (Button) findViewById(R.id.btnMathRate);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, setting_activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ring != null) {
            ring.stop();
            if (isFinishing()) {
                ring.stop();
                ring.release();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ring != null && !ring.isPlaying())
            ring.start();
            ring.setLooping(true);
    }
}