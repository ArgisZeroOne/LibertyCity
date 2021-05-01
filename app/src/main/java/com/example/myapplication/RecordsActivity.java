package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class RecordsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        RecordsAdapter adapter = new RecordsAdapter(this, makeRecord());
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    private void saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
    }

    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }

    public void storeIntArray(String name, int[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("NAME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count_" + name, array.length);
        int count = 0;
        for (int i : array) {
            edit.putInt("IntValue_" + name + count++, i);
        }
        edit.commit();
    }

    public int[] getFromPrefs(String name) {
        int[] ret;
        SharedPreferences prefs = getSharedPreferences("NAME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count_" + name, 0);
        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getInt("IntValue_" + name + i, i);
        }
        return ret;
    }
    public void Click(View navView) {

        switch (navView.getId()) {

            case R.id.back_button:
                Intent intent_menu = new Intent(RecordsActivity.this, MenuActivity.class);
                startActivity(intent_menu);
                break;
        }
    }
    MyRecords[] makeRecord() {


        String[] namesArr = loadArrayList("names").toArray(new String[0]);
        int[] scrArr = getFromPrefs("records");
        int[] scrfornames = new int[namesArr.length];
        for (int i = 0; i < scrfornames.length; i++) {
            scrfornames[i] = scrArr[i];
        }
        ;
        MyRecords[] arr = new MyRecords[namesArr.length];

        for (int i = 0; i < namesArr.length; i++) {
            MyRecords records = new MyRecords();
            records.name = namesArr[i];
            records.score = scrfornames[i];

            arr[i] = records;
        }

        for (int i = 0; i < scrArr.length; i++) {
            for (int j = 0; j < scrArr.length - 1; j++) {
                if (arr[j].score < arr[j + 1].score) {
                    MyRecords temp = new MyRecords();
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}