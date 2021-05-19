package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button_settings:
                Intent intent_menu = new Intent(SettingsActivity.this, MenuActivity.class);
                startActivity(intent_menu);
                break;
            case R.id.lang_btn:

                break;
            case R.id.theme_btn:
             
                break;

        }
    }
}
