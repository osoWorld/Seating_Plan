package com.example.teacherapp.teacherPanel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewDutyStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentTeacherDutySheetBinding;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
import com.example.teacherapp.modelClass.ViewDutySheetModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherDutySheetFragment extends Fragment {
    private FragmentTeacherDutySheetBinding binding;
    private ArrayList<AssignedSeatModelClass> list;
    private ArrayList<ViewDutySheetModelClass> datalist;
    private ViewDutyStudentListAdapter adapter;

    private DatabaseReference reference;

    FirebaseAuth auth;
    FirebaseUser user;
    String currentuserloginID;

    public TeacherDutySheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherDutySheetBinding.inflate(inflater,container,false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
//            Toast.makeText(requireContext(), ""+userId, Toast.LENGTH_SHORT).show();
            GetTeacherData(userId);
            // Now you have the current user's ID (userId)
        } else {
            Log.d( "idnull","null");

            // User is not logged in
        }
        return binding.getRoot();
    }
    private void GetTeacherData(String userID) {
        list = new ArrayList<>();
        datalist = new ArrayList<>();

        adapter = new ViewDutyStudentListAdapter(datalist, requireContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference(
                "TeacherAssignDuty").child("Details");

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                datalist.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DutyDetailsModeClass model = snapshot1.getValue(DutyDetailsModeClass.class);
                    String stdID = model.getStudentID();
                    String seatnumber = model.getStudentseatnumber();
                    if (stdID == null) {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show();

                    } else {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
                        ref.child(stdID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    TeacherStudentListModelClass obj = snapshot.getValue(TeacherStudentListModelClass.class);
                                    String logMessage = "Log 1: " + obj.getUserName() + ", Log 2: " + obj.getStudentDepartment();
                                    Log.d("TAG", logMessage);
                                    ViewDutySheetModelClass obj2 = new ViewDutySheetModelClass(obj.getUid(),seatnumber,obj.getUserName(),obj.getStudentDepartment());
                                    datalist.add(obj2);

                                    binding.progressB.setVisibility(View.GONE);

                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(requireContext(), "FT" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressB.setVisibility(View.GONE);
                            }
                        });
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "FT2" + error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressB.setVisibility(View.GONE);

            }
        });


        binding.assignSeatsRecView.setAdapter(adapter);
        binding.assignSeatsRecView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

    }



    }



