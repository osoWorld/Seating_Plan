package com.example.teacherapp.teacherPanel.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.adminFragment.AssignedSeatsFragment;
import com.example.teacherapp.adminPanel.adminFragment.UnAssignSeatFragment;
import com.example.teacherapp.teacherPanel.fragments.AttendancePresentStudentFragment;
import com.example.teacherapp.teacherPanel.fragments.AttendaneAbsentFragment;

public class AttendanceViewPagerAdapter
 extends FragmentPagerAdapter {

    public AttendanceViewPagerAdapter(
@NonNull FragmentManager fm)
    { 
        super(fm); 
    } 
  
    @NonNull
    @Override
    public Fragment getItem(int position)
    { 
        Fragment fragment = null; 
        if (position == 0)
            fragment = new AttendaneAbsentFragment();
        else if (position == 1) 
            fragment = new AttendancePresentStudentFragment();


  
        return fragment; 
    } 
  
    @Override
    public int getCount() 
    { 
        return 2;
    } 
  
    @Override
    public CharSequence getPageTitle(int position) 
    { 
        String title = null; 
        if (position == 0) 
            title = "Absent";
        else if (position == 1) 
            title = "Present";
        return title; 
    } 
} 