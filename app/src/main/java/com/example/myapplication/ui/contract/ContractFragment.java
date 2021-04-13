package com.example.myapplication.ui.contract;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.myapplication.R.layout.fragment_care;
import static com.example.myapplication.R.layout.fragment_contract;
import static com.example.myapplication.R.layout.fragment_create;

public class ContractFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View games_view = inflater.inflate(fragment_contract, container, false);
        return games_view;
    }

}