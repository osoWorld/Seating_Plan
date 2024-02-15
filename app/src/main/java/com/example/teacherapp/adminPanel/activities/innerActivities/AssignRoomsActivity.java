package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatsAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityAssignRoomsBinding;

import java.util.ArrayList;

public class AssignRoomsActivity extends AppCompatActivity {
    private ActivityAssignRoomsBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignRoomsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","Computer Science"));
        list.add(new TeacherStudentListModelClass(R.drawable.user_pro,"Shahroz","123456","Computer Science"));

        AssignRoomAdapter adapter = new AssignRoomAdapter(list,this);

        binding.assignRoomRecyclerView.setAdapter(adapter);
        binding.assignRoomRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}