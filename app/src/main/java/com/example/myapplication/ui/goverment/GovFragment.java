package com.example.myapplication.ui.goverment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
                TextView citylevel = (TextView) root.findViewById(R.id.cityprgs_txt);
                TextView scores = (TextView) root.findViewById(R.id.scores);
                MainActivity.Values val = new MainActivity.Values();
                String money_str = Integer.toString(val.money);
                String trsrycsts_str = Integer.toString(val.salary + val.taxessalary - val.constantpays - val.centerpays + MainActivity.Store());
                String salary_str = Integer.toString(val.salary + MainActivity.Store());
                String taxespay_str = Integer.toString(val.taxessalary);
                String centerpay_str = Integer.toString(val.centerpays);
                String constpay_str = Integer.toString(val.constantpays);
                String citylevel_str = Integer.toString(val.citylevel);
                String scores_str = Integer.toString(val.scores);
                trsrycsts.setText(getString(R.string.Receipts) + " " + trsrycsts_str + getString(R.string.credits));
                scores.setText(getString(R.string.scores) + " " + scores_str);
                money.setText(getString(R.string.tresuary) + " " + money_str + getString(R.string.credits));
                salary.setText(getString(R.string.revenue) + " " + salary_str + getString(R.string.credits));
                taxespay.setText(getString(R.string.taxes) + " " + taxespay_str + getString(R.string.credits));
                citylevel.setText(getString(R.string.citylevel) + " " + citylevel_str);
                centerpay.setText(getString(R.string.centerpay) + " " + centerpay_str + getString(R.string.credits));
                constpay.setText(getString(R.string.constantpays) + " " + constpay_str + getString(R.string.credits));
                cityprogress.setProgress(val.cityprogress);
                cityhappy.setProgress((int) val.cityhappy);
                citycondition.setProgress((int) val.citycondition);
                suspension.setProgress(val.suspension);
                ProgressBar suspension_tool = (ProgressBar) root.findViewById(R.id.susp_prgsbar_tool);
                ProgressBar cityhappy_tool = (ProgressBar) root.findViewById(R.id.hppy_prgsbar_tool);
                ProgressBar citycondition_tool = (ProgressBar) root.findViewById(R.id.citycond_prgsbar_tool);
                cityhappy_tool.setProgress((int) val.cityhappy);
                citycondition_tool.setProgress((int) val.citycondition);
                suspension_tool.setProgress(val.suspension);
                Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarfrag);

            }

            @Override
            public void onFinish() {
                Timer(root);
            }
        }.start();

    }

    private Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gov, container, false);

        toolbar = root.findViewById(R.id.toolbarfrag);

        Timer(root);
        return root;
    }


}