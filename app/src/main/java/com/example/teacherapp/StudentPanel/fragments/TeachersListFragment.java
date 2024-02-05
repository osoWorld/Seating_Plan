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
        binding = FragmentTeachersListBinding.inflate(getLayoutInflater(),container,false);

        ArrayList<TeachersListModelClass> tList = new ArrayList<>();
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Shahroz Hashmi"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Muhammad Usman"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Saad Bari"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Muhammad Suffian"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Cheema Mobiles"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Abdullah Chattha"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Ameeq"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Pawa"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Haseeb"));
        tList.add(new TeachersListModelClass(R.drawable.user_pro,"Ahmad"));

        TeachersListAdapter teachersListAdapter = new TeachersListAdapter(tList,getContext());

        binding.teachersListRecyclerView.setAdapter(teachersListAdapter);
        binding.teachersListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }
}