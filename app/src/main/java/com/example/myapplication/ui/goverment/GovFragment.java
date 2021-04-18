package com.example.myapplication.ui.goverment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class GovFragment extends Fragment {
    public void Timer(View root) {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
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
                MainActivity.Values val = new MainActivity.Values();
                String money_str = Integer.toString(val.money);
                String trsrycsts_str = Integer.toString(val.salary + val.taxessalary - val.constantpays - val.centerpays);
                String salary_str = Integer.toString(val.salary);
                String taxespay_str = Integer.toString(val.taxessalary);
                String centerpay_str = Integer.toString(val.centerpays);
                String constpay_str = Integer.toString(val.constantpays);
                trsrycsts.setText("Поступления: " + trsrycsts_str);
                money.setText("Итого в казне: " + money_str);
                salary.setText("Выручка: + " + salary_str);
                taxespay.setText("Налоги: + " + taxespay_str);
                centerpay.setText("Отчисления в центр: - " + centerpay_str);
                constpay.setText("Постоянные: - " + constpay_str);
                cityprogress.setProgress(val.cityprogress);
                cityhappy.setProgress((int)val.cityhappy);
                citycondition.setProgress((int)val.citycondition);
                suspension.setProgress(val.suspension);
                MainActivity.Values.cityhappy -=0.25;
                MainActivity.Values.citycondition -=0.25;
            }

            @Override
            public void onFinish() {
                MainActivity.Values.suspension += 5;
                Timer(root);
            }
        }.start();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gov, container, false);
        Timer(root);
        return root;
    }


}