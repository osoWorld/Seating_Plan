package com.example.teacherapp.adminPanel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.adminFragment.AssignRoomsFragment;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomStudentAdapter;
import com.example.teacherapp.databinding.ActivityStudentAssignSeatsBinding;

public class StudentAssignSeatsActivity extends AppCompatActivity {
    private ActivityStudentAssignSeatsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAssignSeatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String department = intent.getStringExtra("studentDepartment");
        String studentName = intent.getStringExtra("studentName");
        String studentId = intent.getStringExtra("studentId");

        if (studentName != null && studentId != null){
            binding.studentName.setText(studentName);
            binding.studentRollNumber.setText(studentId);
        }

        if (department != null) {

            if (department.equals("Computer Science")) {
                binding.departmentImage.setImageResource(R.drawable.cs);
            } else if (department.equals("Information Technology")) {
                binding.departmentImage.setImageResource(R.drawable.information_technology);
            } else if (department.equals("Cyber Security")) {
                binding.departmentImage.setImageResource(R.drawable.cyber_security);
            } else if (department.equals("Artificial Intelligence")) {
                binding.departmentImage.setImageResource(R.drawable.artificial_intelligence);
            } else {
                binding.departmentImage.setImageResource(R.drawable.cs);
            }
        }

        ChangeFragment(new AssignRoomsFragment());
    }

    public void ChangeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameAssignsContainers, fragment).commit();
    }
}