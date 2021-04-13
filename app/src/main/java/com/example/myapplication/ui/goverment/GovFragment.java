package com.example.myapplication.ui.goverment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class GovFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gov, container, false);
        TextView money = (TextView) root.findViewById(R.id.total_txt);
        TextView salary = (TextView) root.findViewById(R.id.reven_txt);
        TextView taxespay = (TextView) root.findViewById(R.id.tax_txt);
        TextView centerpay = (TextView) root.findViewById(R.id.cntrcsts_txt);
        TextView constpay = (TextView) root.findViewById(R.id.constcsts_txt);
        ProgressBar cityprogress = (ProgressBar) root.findViewById(R.id.cityprgs_prgsbar);
        ProgressBar suspension = (ProgressBar) root.findViewById(R.id.susp_prgsbar);
        ProgressBar cityhappy = (ProgressBar) root.findViewById(R.id.hppy_prgsbar);
        ProgressBar citycondition = (ProgressBar) root.findViewById(R.id.citycond_prgsbar);
        MainActivity.Values val = new MainActivity.Values();
        String money_str = Integer.toString(val.money);
        String salary_str = Integer.toString(val.salary);
        String taxespay_str = Integer.toString(val.taxessalary);
        String centerpay_str = Integer.toString(val.centerpays);
        String constpay_str = Integer.toString(val.constantpays);
        money.setText(money_str);
        salary.setText(salary_str);
        taxespay.setText(taxespay_str);
        centerpay.setText(centerpay_str);
        constpay.setText(constpay_str);
        cityprogress.setProgress(val.cityprogress);
        cityhappy.setProgress(val.cityhappy);
        citycondition.setProgress(val.citycondition);
        suspension.setProgress(val.suspension);
        return root;
    }


}