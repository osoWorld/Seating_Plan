package com.example.teacherapp.teacherPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.SignupActivity;
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

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherDashboardActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finishAffinity();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}