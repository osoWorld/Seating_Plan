package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatStudentAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.databinding.FragmentAssignSeatsBinding;

import java.util.ArrayList;

public class AssignSeatsFragment extends Fragment {
    private FragmentAssignSeatsBinding binding;
    private ArrayList<AssignRoomModelClass> list;
    public AssignSeatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssignSeatsBinding.inflate(inflater,container,false);

        list = new ArrayList<>();

        list.add(new AssignRoomModelClass(R.drawable.chair,"1"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"2"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"3"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"4"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"5"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"6"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"7"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"8"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"9"));
        list.add(new AssignRoomModelClass(R.drawable.chair,"10"));

        AssignSeatStudentAdapter adapter = new AssignSeatStudentAdapter(list,getContext());

//        binding.seatAssignRecyclerView.setAdapter(adapter);
//        binding.seatAssignRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        return binding.getRoot();
    }
}