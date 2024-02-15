package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomTeacherAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.databinding.FragmentAssignRoomTeacherBinding;

import java.util.ArrayList;

public class AssignRoomTeacherFragment extends Fragment {
    private FragmentAssignRoomTeacherBinding binding;
    private ArrayList<AssignRoomModelClass> mList;
    public AssignRoomTeacherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssignRoomTeacherBinding.inflate(inflater,container,false);

        mList = new ArrayList<>();

        mList.add(new AssignRoomModelClass(R.drawable.room_door,"24"));
        mList.add(new AssignRoomModelClass(R.drawable.room_door,"25"));
        mList.add(new AssignRoomModelClass(R.drawable.room_door,"26"));
        mList.add(new AssignRoomModelClass(R.drawable.room_door,"27"));

        AssignRoomTeacherAdapter adapter = new AssignRoomTeacherAdapter(mList,getContext());

        binding.assignSeatRoomRecView.setAdapter(adapter);
        binding.assignSeatRoomRecView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return binding.getRoot();
    }
}