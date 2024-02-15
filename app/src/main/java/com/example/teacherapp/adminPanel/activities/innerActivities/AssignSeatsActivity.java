package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatsAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityAssignSeatsBinding;

import java.util.ArrayList;

public class AssignSeatsActivity extends AppCompatActivity {
    private ActivityAssignSeatsBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignSeatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Hussain","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Ameeq","123456","Information Technology"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Zohaib","123456","Information Technology"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Abdul","123456","Cyber Security"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Abdullah","123456","Cyber Security"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Mahad","123456","Artificial Intelligence"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Raibal Butt","123456","Artificial Intelligence"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Ehsan","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Ahmad","123456","Artificial Intelligence"));

        AssignSeatsAdapter adapter = new AssignSeatsAdapter(list,this);

        binding.assignSeatsRecView.setAdapter(adapter);
        binding.assignSeatsRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}