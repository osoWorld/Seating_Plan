package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityTeacherListBinding;

import java.util.ArrayList;

public class TeacherListActivity extends AppCompatActivity {
    private ActivityTeacherListBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","32 - C"));

        TeacherStudentListAdapter adapter = new TeacherStudentListAdapter(list,this);

        binding.teachersListAdminRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.teachersListAdminRecyclerView.setAdapter(adapter);

    }
}