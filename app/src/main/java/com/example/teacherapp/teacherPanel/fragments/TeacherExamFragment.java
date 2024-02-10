package com.example.teacherapp.teacherPanel.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentTeacherExamBinding;
import com.example.teacherapp.teacherPanel.activities.StudentAttendanceActivity;

public class TeacherExamFragment extends Fragment {
    private FragmentTeacherExamBinding binding;
    public TeacherExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherExamBinding.inflate(inflater,container,false);

        binding.studentAttendanceSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentAttendanceActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}