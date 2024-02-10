package com.example.teacherapp.teacherPanel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentTeacherDutySheetBinding;

public class TeacherDutySheetFragment extends Fragment {
    private FragmentTeacherDutySheetBinding binding;
    public TeacherDutySheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherDutySheetBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}