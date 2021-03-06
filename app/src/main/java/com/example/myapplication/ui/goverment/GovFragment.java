package com.example.myapplication.ui.goverment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class GovFragment extends Fragment {



    public void Timer(View root) { // таймер для обновления данных фрагмента

        new CountDownTimer(60000, 1000) { // таймер на 60 секунд с интервалом в 1 секунду

            @Override
            public void onTick(long l) {
                // ↓ Инициализация элементов UI
                TextView money = (TextView) root.findViewById(R.id.total_txt);
                TextView salary = (TextView) root.findViewById(R.id.reven_txt);
                TextView taxespay = (TextView) root.findViewById(R.id.tax_txt);
                TextView centerpay = (TextView) root.findViewById(R.id.cntrcsts_txt);
                TextView constpay = (TextView) root.findViewById(R.id.constcsts_txt);
                TextView trsrycsts = (TextView) root.findViewById(R.id.trsrycsts_txt);
                ProgressBar cityprogress = (ProgressBar) root.findViewById(R.id.cityprgs_prgsbar);
                ProgressBar suspension = (ProgressBar) root.findViewById(R.id.susp_prgsbar);
                ProgressBar cityhappy = (ProgressBar) root.findViewById(R.id.hppy_prgsbar);
                ProgressBar citycondition = (ProgressBar) root.findViewById(R.id.citycond_prgsbar);
                TextView citylevel = (TextView) root.findViewById(R.id.cityprgs_txt);
                TextView scores = (TextView) root.findViewById(R.id.scores);
                ProgressBar suspension_tool = (ProgressBar) root.findViewById(R.id.susp_prgsbar_tool);
                ProgressBar cityhappy_tool = (ProgressBar) root.findViewById(R.id.hppy_prgsbar_tool);
                ProgressBar citycondition_tool = (ProgressBar) root.findViewById(R.id.citycond_prgsbar_tool);
                MainActivity.Values val = new MainActivity.Values(); // считать данные из MainActivity
                // ↓ создание промежуточных строк с данными
                String money_str = Integer.toString(val.money);
                String trsrycsts_str = Integer.toString(val.salary + val.taxessalary - val.constantpays - val.centerpays + MainActivity.Store());
                String salary_str = Integer.toString(val.salary + MainActivity.Store());
                String taxespay_str = Integer.toString(val.taxessalary);
                String centerpay_str = Integer.toString(val.centerpays);
                String constpay_str = Integer.toString(val.constantpays);
                String citylevel_str = Integer.toString(val.citylevel);
                String scores_str = Integer.toString(val.scores);
                // ↓ обновление данных
                trsrycsts.setText( " " + trsrycsts_str + " " );
                scores.setText(" " + scores_str+ " " );
                money.setText(" " + money_str + " " );
                salary.setText(" " + salary_str + " " );
                taxespay.setText(" " + taxespay_str + " " );
                citylevel.setText(" " + citylevel_str+ " " );
                centerpay.setText(" " + centerpay_str + " " );
                constpay.setText( " " + constpay_str + " " );
                cityprogress.setProgress(val.cityprogress);
                cityhappy.setProgress((int) val.cityhappy);
                citycondition.setProgress((int) val.citycondition);
                suspension.setProgress(val.suspension);
                cityhappy_tool.setProgress((int) val.cityhappy);
                citycondition_tool.setProgress((int) val.citycondition);
                suspension_tool.setProgress(val.suspension);


            }

            @Override
            public void onFinish() {
                Timer(root); // рекурсивный перезапуск таймера
            }
        }.start();

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // инициализация фрагмента
        View root = inflater.inflate(R.layout.fragment_gov, container, false);
        Timer(root); // запуск таймера
        return root; // возврат view фрагмента
    }


}