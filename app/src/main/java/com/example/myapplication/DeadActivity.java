package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class DeadActivity extends AppCompatActivity {

    void SelectTheme() {
        SharedPreferences sp = getSharedPreferences("my_settings", Context.MODE_PRIVATE); // инициализация БД настроек
        boolean hasDark = sp.getBoolean("hasDark", true); // получение настроек
        if (hasDark) setTheme(R.style.ThemeMyApplicationDark); // выбор тёмной темы
        if (!hasDark) setTheme(R.style.ThemeMyApplication); // выбор светлой темы
    } // метод выбор темы оформеления

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SelectTheme(); // выбор темы оформления
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead);
        getSupportActionBar().hide();
    } // метод создания Activity

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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.restart_btn:
                // ↓ возврат в MainActivity
                Intent intent_restart = new Intent(DeadActivity.this, MainActivity.class);
                startActivity(intent_restart);
                this.finish();
                break;
            case R.id.record_btn:
                // ↓ запись рекорда в БД
                int[] arry = getFromPrefs("records");
                int[] inter = new int[arry.length + 1];
                for (int i = 0; i < arry.length; i++) {
                    inter[i] = arry[i];
                }
                ;
                inter[arry.length] = MainActivity.Values.tempscore;
                storeIntArray("records", inter);
                EditText text = (EditText) findViewById(R.id.EnterName);
                ArrayList<String> arr = loadArrayList("names");
                arr.add(String.valueOf(text.getText()));
                String Name = "";
                for (int i = 0; i < arr.size(); i++) {
                    Name = Name + arr.get(i) + " " + inter[i] + " ";
                }
                saveArrayList("names", arr);
                // ↓ возврат в MainActivity
                Intent intent_record = new Intent(DeadActivity.this, MainActivity.class);
                startActivity(intent_record);
                this.finish();
                break;
            case R.id.exit_btn:
                // ↓ возврат в главное меню
                Intent intent_exit = new Intent(DeadActivity.this, MenuActivity.class);
                startActivity(intent_exit);
                this.finish();
                break;
        }
    } // обработчик нажатий
}
