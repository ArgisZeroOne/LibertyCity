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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead);
        getSupportActionBar().hide();
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
    public void storeIntArray(String name, int[] array){
        SharedPreferences.Editor edit = getSharedPreferences("NAME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count_" + name, array.length);
        int count = 0;
        for (int i: array){
            edit.putInt("IntValue_" + name + count++, i);
        }
        edit.commit();
    }
    public int[] getFromPrefs(String name){
        int[] ret;
        SharedPreferences prefs = getSharedPreferences("NAME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count_" + name, 0);
        ret = new int[count];
        for (int i = 0; i < count; i++){
            ret[i] = prefs.getInt("IntValue_"+ name + i, i);
        }
        return ret;
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.restart_btn:
                Intent intent_restart = new Intent(DeadActivity.this, MainActivity.class);
                startActivity(intent_restart);
                this.finish();
                break;
            case R.id.record_btn:
                int[] arry = getFromPrefs("records");
                int[] inter = new int[arry.length + 1];
                for (int i = 0; i < arry.length; i++){
                    inter[i] = arry[i];
                };
                inter[arry.length] = MainActivity.Values.tempscore;
                storeIntArray("records", inter);
                EditText text = (EditText) findViewById(R.id.EnterName);
                ArrayList<String> arr = loadArrayList("names");
                arr.add(String.valueOf(text.getText()));
                String catName = "";
                for (int i = 0; i < arr.size(); i++) {
                    catName = catName + arr.get(i) + " " + inter[i] + " ";
                }
                saveArrayList("names", arr);
                Intent intent_record = new Intent(DeadActivity.this, MainActivity.class);
                startActivity(intent_record);
                this.finish();
                break;
            case R.id.exit_btn:
                Intent intent_exit = new Intent(DeadActivity.this, MenuActivity.class);
                startActivity(intent_exit);
                this.finish();
                break;
        }
    }
}
