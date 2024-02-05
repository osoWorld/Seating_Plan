package com.example.teacherapp.StudentPanel.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.StudentDetailCheckActivity;
import com.example.teacherapp.StudentPanel.activities.StudentsDashboardActivity;
import com.example.teacherapp.databinding.FragmentSeatingBinding;

public class SeatingFragment extends Fragment {
    private FragmentSeatingBinding binding;

    public SeatingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSeatingBinding.inflate(getLayoutInflater(), container, false);

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), StudentDetailCheckActivity.class));
            }
        });


        return binding.getRoot();
    }
}