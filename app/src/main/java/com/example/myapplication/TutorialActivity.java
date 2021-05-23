package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        getSupportActionBar().hide();
    }
    public void TutorialClick(View navView) {
        Intent intent_start = new Intent(TutorialActivity.this, MainActivity.class);
        Button skip = (Button) findViewById(R.id.skip);
        Button gototutor1 = (Button) findViewById(R.id.gototutor1);
        Button gototutor2 = (Button) findViewById(R.id.gototutor2);
        Button gototutor3 = (Button) findViewById(R.id.gototutor3);
        Button gotogame = (Button) findViewById(R.id.gotogame);
        ImageView pretutor = (ImageView) findViewById(R.id.pretutor);
        ImageView tutor1 = (ImageView) findViewById(R.id.tutor1);
        ImageView tutor2 = (ImageView) findViewById(R.id.tutor2);
        ImageView tutor3 = (ImageView) findViewById(R.id.tutor3);
        switch (navView.getId()) {
            case R.id.skip:
                startActivity(intent_start);
                break;
            case R.id.gototutor1:
                pretutor.setVisibility(View.GONE);
                gototutor1.setVisibility(View.GONE);
                gototutor2.setVisibility(View.VISIBLE);
                skip.setVisibility(View.GONE);
                break;
            case R.id.gototutor2:
                tutor1.setVisibility(View.GONE);
                gototutor2.setVisibility(View.GONE);
                gototutor3.setVisibility(View.VISIBLE);
                break;
            case R.id.gototutor3:
                tutor2.setVisibility(View.GONE);
                gototutor3.setVisibility(View.GONE);
                gotogame.setVisibility(View.VISIBLE);
                break;
            case R.id.gotogame:
                startActivity(intent_start);
                break;
            default:

                break;
        }
    } // обработчик нажатий
}
