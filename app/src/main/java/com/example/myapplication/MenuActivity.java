package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.androidadvance.topsnackbar.TSnackbar;

public class MenuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.start:
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:

                break;
            case R.id.exit:
                moveTaskToBack(true);
                break;
        }
    }

}
