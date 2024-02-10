package com.example.teacherapp.teacherPanel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentTeacherHomeBinding;

public class TeacherHomeFragment extends Fragment {
    private FragmentTeacherHomeBinding binding;
    public TeacherHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherHomeBinding.inflate(inflater,container,false);




        return binding.getRoot();
    }
}