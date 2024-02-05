package com.example.teacherapp.StudentPanel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentWelcomeBinding;

import java.util.ArrayList;

public class WelcomeFragment extends Fragment {
    private FragmentWelcomeBinding binding;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(getLayoutInflater(),container,false);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        // Adding SlideModel objects to the list
        imageList.add(new SlideModel(R.drawable.riphah1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.riphah2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.riphah3, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.riphah4, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.riphah5, ScaleTypes.FIT));


        return binding.getRoot();
    }
}