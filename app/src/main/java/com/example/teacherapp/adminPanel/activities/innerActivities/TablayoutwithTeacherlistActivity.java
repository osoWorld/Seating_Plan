package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.StudentListViewPagerAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherListViewPagerAdapter;
import com.example.teacherapp.databinding.ActivityTablayoutwithTeacherlistBinding;

public class TablayoutwithTeacherlistActivity extends AppCompatActivity {
    private ActivityTablayoutwithTeacherlistBinding binding;
    private TeacherListViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTablayoutwithTeacherlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));
        String data = getIntent().getStringExtra("RoomKey");
        Bundle bundle = new Bundle();
        bundle.putString("RoomData", data);
        adapter = new TeacherListViewPagerAdapter(
                getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}