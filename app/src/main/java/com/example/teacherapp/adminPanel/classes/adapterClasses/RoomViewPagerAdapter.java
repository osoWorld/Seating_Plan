package com.example.teacherapp.adminPanel.classes.adapterClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teacherapp.adminPanel.adminFragment.AssignedSeatsFragment;
import com.example.teacherapp.adminPanel.adminFragment.Room121Fragment;
import com.example.teacherapp.adminPanel.adminFragment.Room122Fragment;
import com.example.teacherapp.adminPanel.adminFragment.Room123Fragment;
import com.example.teacherapp.adminPanel.adminFragment.Room124Fragment;
import com.example.teacherapp.adminPanel.adminFragment.UnAssignSeatFragment;

public class RoomViewPagerAdapter
 extends FragmentPagerAdapter {

    public RoomViewPagerAdapter(
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
            fragment = new Room121Fragment();
        else if (position == 1) 
            fragment = new Room122Fragment();
        else if (position == 2)
            fragment = new Room123Fragment();
        else if (position == 3)
            fragment = new Room124Fragment();


  
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
            title = "121";
        else if (position == 1) 
            title = "122";
        else if (position == 2)
            title = "123";
        else if (position == 3)
            title = "124";
        return title; 
    } 
} 