package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentAbsentBinding;

public class AbsentFragment extends Fragment {
    private FragmentAbsentBinding binding;
    public AbsentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAbsentBinding.inflate(inflater,container,false);



        return binding.getRoot();
    }
}