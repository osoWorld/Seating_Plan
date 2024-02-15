package com.example.teacherapp.adminPanel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.adminFragment.AssignRoomsFragment;
import com.example.teacherapp.databinding.ActivityMainAssignRoomBinding;

public class MainAssignRoomActivity extends AppCompatActivity {
    private ActivityMainAssignRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.teacherapp.databinding.ActivityMainAssignRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String teacherName = intent.getStringExtra("teacherName");
        String teacherRollNumber = intent.getStringExtra("teacherRollNumber");
        String teacherDepartment = intent.getStringExtra("teacherDepartment");

        int teacherImg = intent.getIntExtra("teacherPic",0);


        binding.assignRoomTeacherImg.setImageResource(teacherImg);

        if (teacherName != null && teacherRollNumber != null && teacherDepartment != null){
            binding.studentName.setText(teacherName);
            binding.studentRollNumber.setText(teacherRollNumber);
        }

        ChangeFragment(new AssignRoomsFragment());
    }

    public void ChangeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameAssignRoomsCont, fragment).commit();
    }
}