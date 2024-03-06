package com.example.teacherapp.teacherPanel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.GetDateSheetData;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentTeacherHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherHomeFragment extends Fragment {
    private FragmentTeacherHomeBinding binding;
    public TeacherHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherHomeBinding.inflate(inflater,container,false);


        GetsheetData();

        return binding.getRoot();
    }
    private void GetsheetData(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DutySheet");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    GetDateSheetData obj = snapshot.getValue(GetDateSheetData.class);
                    binding.termID.setText(obj.getExamType());
                    binding.departmentID.setText(obj.getDepartment());
                    Glide.with(requireContext()).load(obj.getImageUrl()).into(binding.imageID);

                    binding.progressB.setVisibility(View.GONE);

                }else{
                    binding.progressB.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressB.setVisibility(View.GONE);
            }
        });

    }
}