package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AboutActivity extends AppCompatActivity {
    void SelectTheme() {
        SharedPreferences sp = getSharedPreferences("my_settings", Context.MODE_PRIVATE); // инициализация БД настроек
        boolean hasDark = sp.getBoolean("hasDark", true); // получение настроек
        if (hasDark) setTheme(R.style.ThemeMyApplicationDark); // выбор тёмной темы
        if (!hasDark) setTheme(R.style.ThemeMyApplication); // выбор светлой темы
    } // метод выбор темы оформеления

    protected void onCreate(Bundle savedInstanceState) {
        SelectTheme(); // выбор темы оформления
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide(); // скрытие ActionBar'а
    } // метод создания Activity

    public void Click(View navView) {

        switch (navView.getId()) {

            case R.id.back_button:
                // ↓ возврат в главное меню
                Intent intent_menu = new Intent(AboutActivity.this, MenuActivity.class);
                startActivity(intent_menu);
                // * //
                break;
        }
    } // обработчик нажатий
}
