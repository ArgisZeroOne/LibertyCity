package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordsAdapter extends ArrayAdapter<MyRecords> {

    public RecordsAdapter(Context context, MyRecords[] arr) {
        super(context, R.layout.adapter_item, arr);
    } // создание Адаптера

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MyRecords records = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }


        ((TextView) convertView.findViewById(R.id.textView)).setText(records.name);
        ((TextView) convertView.findViewById(R.id.textView2)).setText(String.valueOf(records.score));

        return convertView;
    } // генерация View содержащего рекорд
}