package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    public void Timer(int view) {

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        }.start();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
        Timer(R.layout.activity_start);
    }
}
