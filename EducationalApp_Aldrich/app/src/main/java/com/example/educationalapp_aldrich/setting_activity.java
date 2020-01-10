package com.example.educationalapp_aldrich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class setting_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Easy", "Hard", "Very Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("game_mode", 100);
                        editor.apply();
                        break;
                    case 1:
                        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor2 = pref2.edit();
                        editor2.putInt("game_mode", 200);
                        editor2.apply();
                        break;
                    case 2:
                        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor3 = pref3.edit();
                        editor3.putInt("game_mode", 300);
                        editor3.apply();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("game_mode", 100);
                editor.apply();
            }
        });

        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"15s", "30s", "45s"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("time_limit", 15);
                        editor.apply();
                        break;
                    case 1:
                        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor2 = pref2.edit();
                        editor2.putInt("time_limit", 30);
                        editor2.apply();
                        break;
                    case 2:
                        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor3 = pref3.edit();
                        editor3.putInt("time_limit", 45);
                        editor3.apply();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("time_limit", 15);
                editor.apply();
            }
        });

        Button save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(setting_activity.this,MainActivity.class);
                startActivity(i);
            }
        });





    }
}
