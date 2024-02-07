
package com.example.teacherapp.StudentPanel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentStudentProfileBinding;

public class StudentProfileFragment extends Fragment {
    private FragmentStudentProfileBinding binding;
    public StudentProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentProfileBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }
}