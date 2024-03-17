package com.example.teacherapp.adminPanel.classes.adapterClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment.AiStudentlistFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment.BsItStudentlistFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment.BscsStudentListFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment.CsStudentListFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminTeacherListFragment.AiTeacherlistFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminTeacherListFragment.BsItTeacherlistFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminTeacherListFragment.BscsTeacherlistFragment;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminTeacherListFragment.CsTeacherListFragment;
import com.example.teacherapp.adminPanel.adminFragment.AssignedSeatsFragment;
import com.example.teacherapp.adminPanel.adminFragment.UnAssignSeatFragment;

public class TeacherListViewPagerAdapter
 extends FragmentPagerAdapter {

    public TeacherListViewPagerAdapter(
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
            fragment =  new BscsTeacherlistFragment();
        else if (position == 1)
            fragment = new BsItTeacherlistFragment();
        else if (position == 2)
            fragment = new AiTeacherlistFragment();
        else if (position == 3)
            fragment = new CsTeacherListFragment();


  
        return fragment; 
    } 
  
    @Override
    public int getCount() 
    { 
        return 4;
    } 
  
    @Override
    public CharSequence getPageTitle(int position) 
    { 
        String title = null;
        if (position == 0)
            title = "Computer Science";
        else if (position == 1)
            title = "Information Technology";
        else if (position == 2)
            title = "Artificial Intelligence";
        else if (position == 3)
            title = "Cyber Security";
        return title; 
    } 
} 