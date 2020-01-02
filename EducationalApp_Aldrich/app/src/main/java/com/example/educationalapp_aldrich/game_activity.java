package com.example.educationalapp_aldrich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class game_activity extends AppCompatActivity {

    TextView time_game;
    TextView score_game;
    TextView result_game;
    TextView question_game;

    ImageButton btnCorrect;
    ImageButton btnIncorrect;

    private SimpleDatabase db;

    boolean isResultCorrect;
    int timelimit = 2;
    private int score = 0;
    private boolean stopTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);
        time_game = (TextView) findViewById(R.id.time_game);
        score_game = (TextView) findViewById(R.id.score_game);
        question_game = (TextView) findViewById(R.id.question_game);
        result_game = (TextView) findViewById(R.id.result_game);

        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnIncorrect = (ImageButton) findViewById(R.id.btnIncorrect);

        timer();
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

        db = new SimpleDatabase(this);
    }

    private void generate_question() {
        isResultCorrect = true;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int result = a + b;
        float f = random.nextFloat();
        if (f > 0.5f) {
            result = random.nextInt(100);
            isResultCorrect = false;
        }
        question_game.setText(a + "+" + b);
        result_game.setText("=" + result);
    }

    public void verifyAnswer(boolean answer) {
        if (answer == isResultCorrect) {
            score += 5;
            score_game.setText("SCORE: " + score);
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        generate_question();
    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                time_game.setText("TIME :" + timelimit);
                timelimit--;
                if (timelimit < 0) {
                    Intent i = new Intent(game_activity.this, score_activity.class);
                    i.putExtra("score", score);
                    db.addScore(score);
                    db.close();
                    startActivity(i);
                    stopTimer = true;
                }
                if (stopTimer == false) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer = false;
        finish();
    }


}
