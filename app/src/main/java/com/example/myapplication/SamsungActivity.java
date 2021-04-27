package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SamsungActivity extends AppCompatActivity {

    public void Timer(int view) {

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SamsungActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        }.start();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samsung);
        getSupportActionBar().hide();
        Timer(R.layout.activity_start);
    }
}