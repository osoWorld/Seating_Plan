package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatsAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentAssignSeatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AssignedSeatsFragment extends Fragment {


    private FragmentAssignSeatsBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    private AssignSeatsAdapter adapter;
    private DatabaseReference reference;
    public AssignedSeatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssignSeatsBinding.inflate(inflater, container, false);
        getSeatData();
        return binding.getRoot();
    }
    private void getSeatData() {
        list = new ArrayList<>();

        adapter = new AssignSeatsAdapter(list, requireContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    TeacherStudentListModelClass model = snapshot1.getValue(TeacherStudentListModelClass.class);
                    if (model.getSeatingStatus().equals("Assigned")) {
                        list.add(model);
                        binding.progressB.setVisibility(View.GONE);

                        Log.d("Det", model.getUserName() + "UID: " + model.getUserId() + "Status" + model.getIsSelected());
                    }
//                    list.add(model);
//                    binding.progressB.setVisibility(View.GONE);
                    Log.d("UserNam", model.getUserName() + " UserId: " + model.getUid());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.assignSeatsRecView.setAdapter(adapter);
        binding.assignSeatsRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

}