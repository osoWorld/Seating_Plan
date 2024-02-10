package com.example.teacherapp.teacherPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.fragments.TeachersListFragment;
import com.example.teacherapp.databinding.ActivityTeacherDashboardBinding;
import com.example.teacherapp.teacherPanel.fragments.TeacherDutySheetFragment;
import com.example.teacherapp.teacherPanel.fragments.TeacherExamFragment;
import com.example.teacherapp.teacherPanel.fragments.TeacherHomeFragment;
import com.example.teacherapp.teacherPanel.fragments.TeacherUpdateProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherDashboardActivity extends AppCompatActivity {
    private ActivityTeacherDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ChangeFragment(new TeacherHomeFragment());
        binding.teacherBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItems = item.getItemId();
                if (menuItems == R.id.home_teacher_bottom) {
                    ChangeFragment(new TeacherHomeFragment());
                } else if (menuItems == R.id.dutySheet_teacher_bottom) {
                    ChangeFragment(new TeacherDutySheetFragment());
                } else if (menuItems == R.id.examDetails_teacher_bottom) {
                    ChangeFragment(new TeacherExamFragment());
                } else if (menuItems == R.id.updateProfile_teacher_bottom) {
                    ChangeFragment(new TeacherUpdateProfileFragment());
                } else {

                }
                return true;
            }
        });
    }

    public void ChangeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.teacherFrame, fragment).commit();
    }
}