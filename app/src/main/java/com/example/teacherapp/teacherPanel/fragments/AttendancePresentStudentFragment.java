package com.example.teacherapp.teacherPanel.fragments;

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
import com.example.teacherapp.databinding.FragmentAttendancePresentStudentBinding;
import com.example.teacherapp.databinding.FragmentTeacherExamBinding;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
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

public class AttendancePresentStudentFragment extends Fragment {

   private FragmentAttendancePresentStudentBinding binding;
    private ArrayList<StudentAttendanceModelClass> list;
    private DatabaseReference reference;
    private StudentAttendanceAdapter adapter;

    String roomData,ID;
    public AttendancePresentStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAttendancePresentStudentBinding.inflate(inflater,container,false);

        //current login Id
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
        list = new ArrayList<>();

        adapter = new StudentAttendanceAdapter(list, requireContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference(
                "TeacherAssignDuty").child("Details");

        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DutyDetailsModeClass model = snapshot1.getValue(DutyDetailsModeClass.class);
                    String userID  = model.getStudentID();
                    roomData = model.getRoomData();


                    if (userID != null) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
                        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    StudentAttendanceModelClass obj = snapshot.getValue(StudentAttendanceModelClass.class);
                                    String status = obj.getStudentAttendanceStatus();
                                    Log.d("userIds",obj.getUserName());
                                    if (status.equals("Present")){
                                        list.add(obj);
                                        binding.progressB.setVisibility(View.GONE);

                                        adapter.notifyDataSetChanged();
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressB.setVisibility(View.GONE);
                            }
                        });

                        binding.progressB.setVisibility(View.GONE);

                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.studentAttendanceSheetRecyclerView.setAdapter(adapter);
        binding.studentAttendanceSheetRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

    }

}