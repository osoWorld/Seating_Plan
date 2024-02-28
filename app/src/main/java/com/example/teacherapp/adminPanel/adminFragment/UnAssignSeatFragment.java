package com.example.teacherapp.adminPanel.adminFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.StudentAssignSeatsActivity;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatsAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentAssignRoomsBinding;
import com.example.teacherapp.databinding.FragmentUnAssignSeatBinding;
import com.example.teacherapp.teacherPanel.classes.modelClasses.SelectedItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UnAssignSeatFragment extends Fragment {

    private FragmentUnAssignSeatBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    private AssignSeatsAdapter adapter;
    private DatabaseReference reference;

    public UnAssignSeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUnAssignSeatBinding.inflate(inflater, container, false);
        binding.seatbtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAssignSeatsButtonClick();
            }
        });
        getSeatData();

        return binding.getRoot();

    }

    private void onAssignSeatsButtonClick() {
        ArrayList<TeacherStudentListModelClass> selectedItems = adapter.getSelected();

        if (selectedItems.isEmpty()) {
            Toast.makeText(getContext(), "No items selected", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getContext(), StudentAssignSeatsActivity.class);
            intent.putExtra("selectedItems", selectedItems);
            startActivity(intent);
        }
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
                    if (model.getSeatingStatus().equals("UnAssigned")) {
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