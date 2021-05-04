package com.example.myapplication.ui.finance;

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

import static com.example.myapplication.R.layout.fragment_finance;

public class FinanceFragment extends Fragment {
    public void Timer(View root) {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {

                MainActivity.Values val = new MainActivity.Values();
                Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarfrag);
                ProgressBar suspension_tool = (ProgressBar) root.findViewById(R.id.susp_prgsbar_tool);
                ProgressBar cityhappy_tool = (ProgressBar) root.findViewById(R.id.hppy_prgsbar_tool);
                ProgressBar citycondition_tool = (ProgressBar) root.findViewById(R.id.citycond_prgsbar_tool);
                cityhappy_tool.setProgress((int)val.cityhappy);
                citycondition_tool.setProgress((int)val.citycondition);
                suspension_tool.setProgress(val.suspension);
            }

            @Override
            public void onFinish() {
                Timer(root);
            }
        }.start();

    }
    public static View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View games_view = inflater.inflate(fragment_finance, container, false);
        Timer(games_view);
        view = games_view;
        return games_view;
    }


}