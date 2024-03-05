package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.databinding.FragmentPresentBinding;

public class PresentFragment extends Fragment {
    private FragmentPresentBinding binding;
    private StudentAttendanceAdapter adapter;
    String roomdata;
    public PresentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBinding.inflate(inflater,container,false);
        Bundle args = getArguments();
        if (args != null) {
            String roomData = args.getString("RoomData", "Default value if key not found");
            Toast.makeText(requireContext(), ""+roomData, Toast.LENGTH_SHORT).show();
        }



        return binding.getRoot();
    }
}