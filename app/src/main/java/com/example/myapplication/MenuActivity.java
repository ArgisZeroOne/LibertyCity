package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuActivity extends AppCompatActivity {

    void SelectTheme() {
        SharedPreferences sp = getSharedPreferences("my_settings", Context.MODE_PRIVATE); // инициализация БД настроек
        boolean hasDark = sp.getBoolean("hasDark", true); // получение настроек
        if (hasDark) setTheme(R.style.ThemeMyApplicationDark); // выбор тёмной темы
        if (!hasDark) setTheme(R.style.ThemeMyApplication); // выбор светлой темы
    } // метод выбор темы оформеления

    private void saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
    } // метод сохранения массива строк в SharedPreferences

    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    } // метод загрузки массива строк из SharedPreferences

    public void storeIntArray(String name, int[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("NAME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count_" + name, array.length);
        int count = 0;
        for (int i : array) {
            edit.putInt("IntValue_" + name + count++, i);
        }
        edit.commit();
    } // метод сохранения массива чисел в SharedPreferences

    public int[] getFromPrefs(String name) {
        int[] ret;
        SharedPreferences prefs = getSharedPreferences("NAME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count_" + name, 0);
        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getInt("IntValue_" + name + i, i);
        }
        return ret;
    } // метод загрузки массива чисел из SharedPreferences

    private static final String MY_SETTINGS = "my_settings"; // имя настроек

    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        SelectTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();


        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited) {
            int[] arry = getFromPrefs("records");
            int[] inter = new int[arry.length + 1];
            for (int i = 0; i < arry.length; i++) {
                inter[i] = arry[i];
            }
            ;
            inter[arry.length] = 0;
            storeIntArray("records", inter);
            EditText text = (EditText) findViewById(R.id.EnterName);
            TextView txt = (TextView) findViewById(R.id.textView2);
            ArrayList<String> arr = loadArrayList("names");
            arr.add("_NoName_");
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
        }
    } // метод создания Activity

    public void MenuClick(View view) {
        switch (view.getId()) {
            case R.id.settings:
                Intent intent_settings = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(intent_settings);
                break;
            case R.id.about:
                Intent intent_about = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent_about);
                break;
            case R.id.start:
                Intent intent_start = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent_start);
                break;
            case R.id.records:
                Intent intent_records = new Intent(MenuActivity.this, RecordsActivity.class);
                startActivity(intent_records);
                break;
            case R.id.store:
                Intent intent_store = new Intent(MenuActivity.this, StoreActivity.class);
                startActivity(intent_store);
                break;
            case R.id.exit:
                moveTaskToBack(true);
                break;


        }
    } // обработчик нажатий

}
