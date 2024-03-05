package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.databinding.FragmentPresentBinding;

public class PresentFragment extends Fragment {
    private FragmentPresentBinding binding;
    private StudentAttendanceAdapter adapter;
    public PresentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBinding.inflate(inflater,container,false);




        return binding.getRoot();
    }
}