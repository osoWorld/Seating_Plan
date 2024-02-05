package com.example.teacherapp.StudentPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.fragments.SeatingFragment;
import com.example.teacherapp.StudentPanel.fragments.StudentProfileFragment;
import com.example.teacherapp.StudentPanel.fragments.TeachersListFragment;
import com.example.teacherapp.StudentPanel.fragments.WelcomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StudentsDashboardActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_dashboard);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ChangeFragment(new WelcomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int menuId = item.getItemId();
                if (menuId == R.id.welcomeBottom){
                    ChangeFragment(new WelcomeFragment());
                } else if (menuId == R.id.seatingListBottom) {
                    ChangeFragment(new SeatingFragment());
                } else if (menuId == R.id.teachersList) {
                    ChangeFragment(new TeachersListFragment());
                } else if (menuId == R.id.profileBottom) {
                    ChangeFragment(new StudentProfileFragment());
                }else {

                }
                return false;
            }
        });
    }



    public void ChangeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrames,fragment).commit();
    }
}