package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.ActivityAdminUpdateProfileBinding;

public class AdminUpdateProfileActivity extends AppCompatActivity {
    private ActivityAdminUpdateProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}