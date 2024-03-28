package com.example.teacherapp.teacherPanel.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentTeacherExamBinding;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
import com.example.teacherapp.teacherPanel.activities.StudentAttendanceActivity;
import com.example.teacherapp.teacherPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherExamFragment extends Fragment {
    private FragmentTeacherExamBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String papername,roomdata;
    public TeacherExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherExamBinding.inflate(inflater,container,false);

        binding.studentAttendanceSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentAttendanceActivity.class);
                startActivity(intent);
            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            getData(userId);
        } else {
            Log.d("idnull", "null");

            // User is not logged in
        }

        return binding.getRoot();
    }
    private void getData(String uid){
        reference = FirebaseDatabase.getInstance().getReference(
                "TeacherAssignDuty").child("Details");

        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DutyDetailsModeClass model = snapshot1.getValue(DutyDetailsModeClass.class);
                    roomdata = model.getRoomData();
                    papername = model.getPapername();


                }
                binding.textView10.setText(papername);
                binding.textView15.setText(roomdata);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}