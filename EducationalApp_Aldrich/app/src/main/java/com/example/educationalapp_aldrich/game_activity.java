package com.example.educationalapp_aldrich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class game_activity extends AppCompatActivity {

    TextView time_game;
    TextView score_game;
    TextView result_game;
    TextView question_game;

    LinearLayout answer_game;

    ImageButton btnCorrect;
    ImageButton btnIncorrect;
    Button unhide;
    ProgressBar remember;

    private SimpleDatabase db;

    boolean isResultCorrect;



    private int score = 0;
    private int health = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        health = pref.getInt("health", 3);
        time_game = (TextView) findViewById(R.id.health_set);
        time_game.setText("HEALTH: " + health);


        score_game = (TextView) findViewById(R.id.score_game);
        question_game = (TextView) findViewById(R.id.question_game);
        result_game = (TextView) findViewById(R.id.result_game);
        answer_game = (LinearLayout) findViewById(R.id.checkon);
        unhide = (Button) findViewById(R.id.unhide);

        unhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unhide_now();
            }
        });

        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnIncorrect = (ImageButton) findViewById(R.id.btnIncorrect);

        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });
        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });
        generate_question();

        answer_game.setVisibility(View.GONE);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        answer_game.setVisibility(View.VISIBLE);
                        question_game.setVisibility(View.GONE);
                        result_game.setVisibility(View.GONE);
                    }
                }, 5000);

        db = new SimpleDatabase(this);
    }

    private void generate_question() {
        isResultCorrect = true;
        Random random = new Random();


        CountDownTimer mCountDownTimer;
        remember = (ProgressBar) findViewById(R.id.progressbar);
        remember.setProgress(0);
        mCountDownTimer=new CountDownTimer(5300,1000) {
            int i=0;

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i + millisUntilFinished);
                i++;
                remember.setProgress((int)i*100/(5300/1000));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                remember.setProgress(100);
            }
        };
        mCountDownTimer.start();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        int a_value = pref.getInt("game_mode", 100);
        int b_value = pref.getInt("game_mode", 100);

        question_game.setVisibility(View.VISIBLE);
        result_game.setVisibility(View.VISIBLE);

        int a = random.nextInt(a_value);
        int b = random.nextInt(b_value);
        int result = a + b;
        float f = random.nextFloat();
        if (f > 0.5f) {
            result = random.nextInt(100);
            isResultCorrect = false;
        }
        question_game.setText(a + "+" + b);
        result_game.setText("=" + result);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // Hide your View after 3 seconds
                question_game.setVisibility(View.GONE);
                result_game.setVisibility(View.GONE);
                answer_game.setVisibility(View.VISIBLE);
            }
        }, 5000);

    }

    public void verifyAnswer(boolean answer) {
        if (answer == isResultCorrect) {
            score += 10;
            score_game.setText("SCORE: " + score);
            question_game.setVisibility(View.VISIBLE);
            result_game.setVisibility(View.VISIBLE);
            answer_game.setVisibility(View.GONE);
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
            question_game.setVisibility(View.VISIBLE);
            result_game.setVisibility(View.VISIBLE);
            answer_game.setVisibility(View.GONE);

            health -= 1;
            if (health < 1) {
                Intent i = new Intent(game_activity.this, score_activity.class);
                i.putExtra("score", score);
                db.addScore(score);
                db.close();
                startActivity(i);
                finish();
            } else {
                time_game.setText("HEALTH: " + health);
            }
        }
        generate_question();
    }

    public void unhide_now() {
        question_game.setVisibility(View.VISIBLE);
        result_game.setVisibility(View.VISIBLE);
        answer_game.setVisibility(View.GONE);
        score -= 5;
        score_game.setText("SCORE: " + score);
        if (score<0) {
            score = 0;
            score_game.setText("SCORE: " + score);
        }

        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {

            @Override
            public void run() {
                answer_game.setVisibility(View.VISIBLE);
                question_game.setVisibility(View.GONE);
                result_game.setVisibility(View.GONE);
            }
        }, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}
