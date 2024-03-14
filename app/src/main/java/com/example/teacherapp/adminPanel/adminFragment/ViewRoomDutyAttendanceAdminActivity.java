package com.example.teacherapp.adminPanel.adminFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.RoomOfStudentAttendanceActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.View122AttendanceActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.View123AttendanceActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.View124AttendanceActivity;

public class ViewRoomDutyAttendanceAdminActivity extends AppCompatActivity {
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_duty_attendance_admin);
        key= getIntent().getStringExtra("RoomKey");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (key.equals("121")){
            startActivity(new Intent(this, RoomOfStudentAttendanceActivity.class));
        }else if(key.equals("122")){
            startActivity(new Intent(this, View122AttendanceActivity.class));

        }else if(key.equals("123")){
            startActivity(new Intent(this, View123AttendanceActivity.class));

        }else if(key.equals("124")){
            startActivity(new Intent(this, View124AttendanceActivity.class));

        }
    }
}