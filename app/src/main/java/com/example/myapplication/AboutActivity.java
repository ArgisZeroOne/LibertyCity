package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AboutActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
    }
    public void Click(View navView) {

        switch (navView.getId()) {

            case R.id.back_button:
                Intent intent_menu = new Intent(AboutActivity.this, MenuActivity.class);
                startActivity(intent_menu);
                break;
        }
    }
}
