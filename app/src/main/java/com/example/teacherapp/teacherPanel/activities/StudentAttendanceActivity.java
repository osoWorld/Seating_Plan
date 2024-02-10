package com.example.teacherapp.teacherPanel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.ActivityStudentAttendanceBinding;
import com.example.teacherapp.teacherPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;

import java.util.ArrayList;

public class StudentAttendanceActivity extends AppCompatActivity {
    private ActivityStudentAttendanceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<StudentAttendanceModelClass> list = new ArrayList<>();
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Suffian","987809","1","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Ameeq","987809","2","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Abdullah","987809","3","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Saad","987809","4","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Saad Bari","987809","5","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Usman","987809","6","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Usama","987809","7","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Dinosaur","987809","8","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Ahmad","987809","9","checked"));
        list.add(new StudentAttendanceModelClass(R.drawable.user_pro,"Pawa","987809","10","checked"));

        StudentAttendanceAdapter adapter = new StudentAttendanceAdapter(list,this);
        binding.studentAttendanceSheetRecyclerView.setAdapter(adapter);
        binding.studentAttendanceSheetRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}