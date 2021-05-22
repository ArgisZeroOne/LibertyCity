package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SettingsActivity extends AppCompatActivity {

    void SelectTheme() {
        SharedPreferences sp = getSharedPreferences("my_settings", Context.MODE_PRIVATE); // инициализация БД настроек
        boolean hasDark = sp.getBoolean("hasDark", true); // получение настроек
        if (hasDark) setTheme(R.style.ThemeMyApplicationDark); // выбор тёмной темы
        if (!hasDark) setTheme(R.style.ThemeMyApplication); // выбор светлой темы
    } // метод выбор темы оформеления

    protected void onCreate(Bundle savedInstanceState) {
        SelectTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
    } // метод создания Activity

    private static final String MY_SETTINGS = "my_settings"; // имя настроек

    public void Click(View navView) {

        switch (navView.getId()) {

            case R.id.back_button_settings:
                Intent intent_menu = new Intent(SettingsActivity.this, MenuActivity.class);
                startActivity(intent_menu);
                break;
            case R.id.theme_btn:
                SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                        Context.MODE_PRIVATE);
                boolean hasDark = sp.getBoolean("hasDark", true);
                if (hasDark) {
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("hasDark", false);
                    e.commit();
                } else {
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("hasDark", true);
                    e.commit();
                }
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
        }
    } // обработчик нажатий

}
