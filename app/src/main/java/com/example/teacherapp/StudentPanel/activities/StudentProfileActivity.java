package com.example.teacherapp.StudentPanel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.ActivityStudentProfileBinding;

public class StudentProfileActivity extends AppCompatActivity {
    private ActivityStudentProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(StudentProfileActivity.this,));
            }
        });
    }
}