package com.example.teacherapp.StudentPanel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.adapterClasses.TeachersListAdapter;
import com.example.teacherapp.StudentPanel.classes.modelClasses.TeachersListModelClass;
import com.example.teacherapp.databinding.FragmentTeachersListBinding;

import java.util.ArrayList;

public class TeachersListFragment extends Fragment {
    private FragmentTeachersListBinding binding;

    public TeachersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeachersListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}