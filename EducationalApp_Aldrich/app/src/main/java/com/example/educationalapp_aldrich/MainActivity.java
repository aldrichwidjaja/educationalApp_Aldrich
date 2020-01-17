package com.example.educationalapp_aldrich;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


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
    public void onTrimMemory(int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            ring.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
            ring.start();
            ring.setLooping(true);
    }


    public void setvolume(View view) {
        if (ring.isPlaying()) {
            ring.pause();
            ImageView volumepause = (ImageView) findViewById(R.id.pausesound);
            volumepause.setImageResource(R.drawable.mutevolume);
        } else {
            ring.start();
            ImageView volumepause = (ImageView) findViewById(R.id.pausesound);
            volumepause.setImageResource(R.drawable.volume);
        }
    }
}

