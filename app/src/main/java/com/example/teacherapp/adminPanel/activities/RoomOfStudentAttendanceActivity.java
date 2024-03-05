package com.example.teacherapp.adminPanel.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewAttendanceAdapter;
import com.example.teacherapp.databinding.ActivityRoomOfStudentAttendanceBinding;

public class RoomOfStudentAttendanceActivity extends AppCompatActivity {
    private ActivityRoomOfStudentAttendanceBinding binding;
    private ViewAttendanceAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomOfStudentAttendanceBinding.inflate(getLayoutInflater());
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


        viewPagerAdapter = new ViewAttendanceAdapter(
                getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);


        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}