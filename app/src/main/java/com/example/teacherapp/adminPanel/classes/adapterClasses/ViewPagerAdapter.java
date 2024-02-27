package com.example.teacherapp.adminPanel.classes.adapterClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.adminFragment.AssignedSeatsFragment;
import com.example.teacherapp.adminPanel.adminFragment.UnAssignSeatFragment;

public class ViewPagerAdapter
 extends FragmentPagerAdapter {
  
    public ViewPagerAdapter( 
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
            fragment = new UnAssignSeatFragment();
        else if (position == 1) 
            fragment = new AssignedSeatsFragment();


  
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
            title = "UnAssigned";
        else if (position == 1) 
            title = "Assigned";
        return title; 
    } 
} 