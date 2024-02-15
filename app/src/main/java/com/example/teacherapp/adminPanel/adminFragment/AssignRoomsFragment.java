package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomStudentAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.databinding.FragmentAssignRoomsBinding;

import java.util.ArrayList;

public class AssignRoomsFragment extends Fragment {
    private FragmentAssignRoomsBinding binding;
    ArrayList<AssignRoomModelClass> list;
    public AssignRoomsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssignRoomsBinding.inflate(inflater,container,false);

        list = new ArrayList<>();

        list.add(new AssignRoomModelClass(R.drawable.room_door,"120"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"124"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"128"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"132"));

        AssignRoomStudentAdapter adapter = new AssignRoomStudentAdapter(list,getContext());

        binding.roomAssignRecyclerView.setAdapter(adapter);
        binding.roomAssignRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return binding.getRoot();
    }
}