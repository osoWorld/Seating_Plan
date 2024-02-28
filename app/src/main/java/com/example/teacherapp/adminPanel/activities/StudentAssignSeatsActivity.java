package com.example.teacherapp.adminPanel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.adminFragment.AssignRoomsFragment;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomStudentAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentAssignSeatsBinding;

import java.util.ArrayList;

public class StudentAssignSeatsActivity extends AppCompatActivity {
    private ActivityStudentAssignSeatsBinding binding;
    private ArrayList<AssignRoomModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAssignSeatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        list = new ArrayList<>();

        list.add(new AssignRoomModelClass(R.drawable.room_door,"120"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"124"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"128"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"132"));

        AssignRoomStudentAdapter adapter = new AssignRoomStudentAdapter(list,this);

        binding.assignSeatRecView.setAdapter(adapter);
        binding.assignSeatRecView.setLayoutManager(new GridLayoutManager(this,2));
        ArrayList<TeacherStudentListModelClass> receivedItems = (ArrayList<TeacherStudentListModelClass>) getIntent().getSerializableExtra("selectedItems");
        for (TeacherStudentListModelClass list :receivedItems){
            Toast.makeText(this, ""+list.getUid(), Toast.LENGTH_SHORT).show();
        }
    }
}