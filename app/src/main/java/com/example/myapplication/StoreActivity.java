package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StoreActivity extends AppCompatActivity {

    void SelectTheme() {
        SharedPreferences sp = getSharedPreferences("my_settings", Context.MODE_PRIVATE); // инициализация БД настроек
        boolean hasDark = sp.getBoolean("hasDark", true); // получение настроек
        if (hasDark) setTheme(R.style.ThemeMyApplicationDark); // выбор тёмной темы
        if (!hasDark) setTheme(R.style.ThemeMyApplication); // выбор светлой темы
    } // метод выбор темы оформеления

    SharedPreferences mySettings; // инициализация SharedPreferences

    public class Settings {
        public static final String APP_PREFERENCES = "mysettings";
        public static final String APP_PREFERENCES_globalscore = "globalscore";
        public static final String APP_PREFERENCES_store_houses = "store_houses";
        public static final String APP_PREFERENCES_store_zdroads = "store_zdroads";
        public static final String APP_PREFERENCES_store_vokzal = "store_vokzal";
        public static final String APP_PREFERENCES_store_zk = "store_zk";
        public static final String APP_PREFERENCES_store_lunapark = "store_lunapark";
        public static final String APP_PREFERENCES_store_interval = "store_interval";
    } // имена настроек в SharedPreferences

    public static class Values {
        public static int store_houses = 0;
        public static int store_zdroads = 0;
        public static int store_vokzal = 0;
        public static int store_zk = 0;
        public static int store_lunapark = 0;
        public static int store_interval = 0;
        public static int globalscore = 0;

    } // основные переменные

    Settings settings = new Settings(); // инициализация настроек

    public void UpdateText() {
        TextView text = (TextView) findViewById(R.id.store_score);
        text.setText(String.valueOf(Values.globalscore));
    } // метод обновления количества очков на экране

    public void SaveValues() {
        SharedPreferences.Editor editor = this.mySettings.edit();
        editor.putString(settings.APP_PREFERENCES_globalscore, String.valueOf(MainActivity.Values.globalscore));
        editor.putString(settings.APP_PREFERENCES_store_houses, String.valueOf(Values.store_houses));
        editor.putString(settings.APP_PREFERENCES_store_zdroads, String.valueOf(Values.store_zdroads));
        editor.putString(settings.APP_PREFERENCES_store_vokzal, String.valueOf(Values.store_vokzal));
        editor.putString(settings.APP_PREFERENCES_store_zk, String.valueOf(Values.store_zk));
        editor.putString(settings.APP_PREFERENCES_store_lunapark, String.valueOf(Values.store_lunapark));
        editor.putString(settings.APP_PREFERENCES_store_interval, String.valueOf(Values.store_interval));
        editor.apply();
        UpdateText();
    } // метод сохранения настроек

    public void LoadValues() {
        Values.globalscore = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_globalscore, "1000"));
        Values.store_houses = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_houses, "0"));
        Values.store_zdroads = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_zdroads, "0"));
        Values.store_vokzal = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_vokzal, "0"));
        Values.store_zk = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_zk, "0"));
        Values.store_lunapark = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_lunapark, "0"));
        Values.store_interval = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_store_interval, "0"));

    } // метод загрузки настроек


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SelectTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences = getSharedPreferences(settings.APP_PREFERENCES, 0);
        mySettings = sharedPreferences;
        LoadValues();
        UpdateText();
    } // метод создания Activity

    public void Click(View navView) {

        switch (navView.getId()) {
            case R.id.store_houses:
                if (Values.globalscore >= 1000) {
                    Values.store_houses += 18;
                    Values.globalscore -= 1000;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                SaveValues();
                break;
            case R.id.store_back:
                SharedPreferences.Editor editor = this.mySettings.edit();
                editor.putString(settings.APP_PREFERENCES_globalscore, String.valueOf(MainActivity.Values.globalscore));
                editor.commit();
                Intent intent_record = new Intent(StoreActivity.this, MenuActivity.class);
                startActivity(intent_record);
                break;
            case R.id.store_zdroads:
                if (Values.globalscore >= 300) {
                    Values.store_houses += 2;
                    Values.globalscore -= 300;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                SaveValues();
                break;
            case R.id.store_vokzal:
                if (Values.globalscore >= 450) {
                    Values.store_houses += 5;
                    Values.globalscore -= 450;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                SaveValues();
                break;
            case R.id.store_zk:
                if (Values.globalscore >= 700) {
                    Values.store_houses += 12;
                    Values.globalscore -= 700;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                SaveValues();
                break;
            case R.id.store_lunapark:
                if (Values.globalscore >= 850) {
                    Values.store_houses += 15;
                    Values.globalscore -= 850;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                UpdateText();
                SaveValues();
                break;
            case R.id.store_interval:
                if (Values.globalscore >= 1000) {
                    Values.store_houses += 100;
                    Values.globalscore -= 1000;
                } else {
                    TSnackbar.make(navView, "Недостаточно средств", TSnackbar.LENGTH_LONG).show();
                }
                SaveValues();
                break;
        }
    } // обработчик нажатий

}
