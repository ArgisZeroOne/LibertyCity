package com.example.myapplication.ui.create;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import static com.example.myapplication.R.layout.fragment_create;

public class CreateFragment extends Fragment {

    public void Timer(View root) { // таймер для обновления данных фрагмента
        new CountDownTimer(60000, 1000) { // таймер на 60 секунд с интервалом в 1 секунду

            @Override
            public void onTick(long l) {
                MainActivity.Values val = new MainActivity.Values(); // считать данные из MainActivity
                // ↓ Инициализация элементов UI
                Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarfrag);
                ProgressBar suspension_tool = (ProgressBar) root.findViewById(R.id.susp_prgsbar_tool);
                ProgressBar cityhappy_tool = (ProgressBar) root.findViewById(R.id.hppy_prgsbar_tool);
                ProgressBar citycondition_tool = (ProgressBar) root.findViewById(R.id.citycond_prgsbar_tool);
                // ↓ обновление данных
                cityhappy_tool.setProgress((int)val.cityhappy);
                citycondition_tool.setProgress((int)val.citycondition);
                suspension_tool.setProgress(val.suspension);
            }

            @Override
            public void onFinish() {
                Timer(root); // рекурсивный перезапуск таймера
            }
        }.start();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // инициализация фрагмента
        View games_view = inflater.inflate(fragment_create, container, false);
        Timer(games_view); // запуск таймера
        return games_view; // возврат view фрагмента
    }

}