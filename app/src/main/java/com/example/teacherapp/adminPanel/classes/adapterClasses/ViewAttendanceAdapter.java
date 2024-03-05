package com.example.teacherapp.adminPanel.classes.adapterClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.adminFragment.AbsentFragment;
import com.example.teacherapp.adminPanel.adminFragment.PresentFragment;

public class ViewAttendanceAdapter extends FragmentPagerAdapter {
    public ViewAttendanceAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new PresentFragment();
        else if (position == 1)
            fragment = new AbsentFragment();

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
