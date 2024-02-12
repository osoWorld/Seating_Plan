package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentListBinding;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private ActivityStudentListBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Suffian","123456","34"));

        TeacherStudentListAdapter adapter = new TeacherStudentListAdapter(list,this);

        binding.studentListAdminRecyclerView.setAdapter(adapter);
        binding.studentListAdminRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}