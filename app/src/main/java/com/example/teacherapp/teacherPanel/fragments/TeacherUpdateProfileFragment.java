package com.example.teacherapp.teacherPanel.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.SignupActivity;
import com.example.teacherapp.databinding.FragmentTeacherUpdateProfileBinding;

public class TeacherUpdateProfileFragment extends Fragment {
    private FragmentTeacherUpdateProfileBinding binding;
    public TeacherUpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherUpdateProfileBinding.inflate(inflater,container,false);

        binding.teacherLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SignupActivity.class));
            }
        });

        return binding.getRoot();
    }
}