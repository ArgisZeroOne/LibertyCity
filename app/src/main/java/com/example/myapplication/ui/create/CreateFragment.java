package com.example.myapplication.ui.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.myapplication.R.layout.fragment_care;
import static com.example.myapplication.R.layout.fragment_create;

public class CreateFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View games_view = inflater.inflate(fragment_create, container, false);
        return games_view;
    }

}