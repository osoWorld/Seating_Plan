package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.adminPanel.classes.adapterClasses.View122AttendanceAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.View123AttendanceAdapter;
import com.example.teacherapp.databinding.ActivityView122AttendanceBinding;
import com.example.teacherapp.databinding.ActivityView123AttendanceBinding;

public class View123AttendanceActivity extends AppCompatActivity {
    private ActivityView123AttendanceBinding binding;
    private View123AttendanceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityView123AttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));
        String data = getIntent().getStringExtra("RoomKey");
        Bundle bundle = new Bundle();
        bundle.putString("RoomData", data);
        adapter = new View123AttendanceAdapter(
                getSupportFragmentManager(),bundle);
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminDashboardActivity.class));
    }
}