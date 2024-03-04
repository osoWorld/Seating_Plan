package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomDutySheetAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomStudentAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.databinding.ActivityUploadDutySheetBinding;

import java.util.ArrayList;

public class UploadDutySheetActivity extends AppCompatActivity implements AssignRoomDutySheetAdapter.OnItemClickListener {
    private ActivityUploadDutySheetBinding binding;
    private ArrayList<AssignRoomModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadDutySheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));


        list = new ArrayList<>();

        list.add(new AssignRoomModelClass(R.drawable.room_door, "121"));
        list.add(new AssignRoomModelClass(R.drawable.room_door, "122"));
        list.add(new AssignRoomModelClass(R.drawable.room_door, "123"));
        list.add(new AssignRoomModelClass(R.drawable.room_door, "124"));

        AssignRoomDutySheetAdapter adapter = new AssignRoomDutySheetAdapter(list, this, this);

        binding.assignSeatRecView.setAdapter(adapter);
        binding.assignSeatRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UploadDutySheetActivity.this, AdminDashboardActivity.class));
    }

    @Override
    public void onItemClick(AssignRoomModelClass roomData) {
        String roomNum = roomData.getRoomName();
        startActivity(new Intent(getApplicationContext(), AssignDutyTOTeacherActivity.class)
                .putExtra("roomkey", roomNum));
    }
}