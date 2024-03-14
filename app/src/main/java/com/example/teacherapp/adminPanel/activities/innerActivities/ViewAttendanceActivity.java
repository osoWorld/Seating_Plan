package com.example.teacherapp.adminPanel.activities.innerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.RoomOfStudentAttendanceActivity;
import com.example.teacherapp.adminPanel.adminFragment.ViewRoomDutyAttendanceAdminActivity;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomDutySheetAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewAttendanceAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewPagerAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.databinding.ActivityViewAttendanceBinding;

import java.util.ArrayList;

public class ViewAttendanceActivity extends AppCompatActivity implements AssignRoomDutySheetAdapter.OnItemClickListener{
    private ActivityViewAttendanceBinding binding;
    private ArrayList<AssignRoomModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewAttendanceBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        binding.presetRoomsRecView.setAdapter(adapter);
        binding.presetRoomsRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onItemClick(AssignRoomModelClass roomData) {
        startActivity(new Intent(this, ViewRoomDutyAttendanceAdminActivity.class).putExtra("RoomKey",roomData.getRoomName()));
    }
}