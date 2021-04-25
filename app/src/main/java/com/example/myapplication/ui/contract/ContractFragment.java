package com.example.myapplication.ui.contract;

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

import static com.example.myapplication.R.layout.fragment_contract;

public class ContractFragment extends Fragment {
    public void Timer(View root) {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                MainActivity.Values val = new MainActivity.Values();
                Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarfrag);
                if (val.hungry == true && val.angry == true) {
                    toolbar.setSubtitle("Город заброшен и народ зол");
                } else if (val.hungry == true && val.angry == false) {
                    toolbar.setSubtitle("Город заброшен, народ снисходителен");
                } else if (val.hungry == false && val.angry == true) {
                    toolbar.setSubtitle("Город ухожен, народ зол");
                } else if (val.hungry == false && val.angry == false) {
                    toolbar.setSubtitle("Город чист, народ счастлив");
                }
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View games_view = inflater.inflate(fragment_contract, container, false);
        Timer(games_view);
        return games_view;
    }

}