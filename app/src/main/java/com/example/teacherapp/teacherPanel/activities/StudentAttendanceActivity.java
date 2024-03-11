package com.example.teacherapp.teacherPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewAttendanceAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentAttendanceBinding;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
import com.example.teacherapp.teacherPanel.classes.AttendanceViewPagerAdapter;
import com.example.teacherapp.teacherPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StudentAttendanceActivity extends AppCompatActivity {
    private ActivityStudentAttendanceBinding binding;
    AttendanceViewPagerAdapter attendanceViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));


        attendanceViewPagerAdapter = new AttendanceViewPagerAdapter(
                getSupportFragmentManager());
        binding.viewPager.setAdapter(attendanceViewPagerAdapter);

        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setupWithViewPager(binding.viewPager);


    }

}