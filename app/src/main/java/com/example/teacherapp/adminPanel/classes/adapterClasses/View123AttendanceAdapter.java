package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.adminFragment.AbsentFragment;
import com.example.teacherapp.adminPanel.adminFragment.PresentFragment;
import com.example.teacherapp.adminPanel.adminFragment.ViewAttendance123AbsentFragment;
import com.example.teacherapp.adminPanel.adminFragment.ViewAttendance123presentFragment;

public class View123AttendanceAdapter extends FragmentPagerAdapter {
    private Bundle dataBundle;
    public View123AttendanceAdapter(@NonNull FragmentManager fm, Bundle dataBundle) {

        super(fm);
        this.dataBundle = dataBundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new ViewAttendance123presentFragment();
        else if (position == 1)
            fragment = new ViewAttendance123AbsentFragment();
        if (fragment != null) {
            fragment.setArguments(dataBundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Present";
        else if (position == 1)
            title = "Absent";
        return title;
    }
}
