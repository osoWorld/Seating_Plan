package com.example.teacherapp.StudentPanel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.adapterClasses.StudentDetailCheckAdapter;
import com.example.teacherapp.StudentPanel.classes.modelClasses.StudentDetailCheckModelClass;
import com.example.teacherapp.databinding.ActivityStudentDetailCheckBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentDetailCheckActivity extends AppCompatActivity {
    private ActivityStudentDetailCheckBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDetailCheckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Instances();


        ArrayList<StudentDetailCheckModelClass> list = new ArrayList<>();

        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"85","1"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"86","2"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"87","3"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"88","4"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"89","5"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"90","6"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"91","7"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"92","8"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"93","9"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"94","10"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"95","11"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"96","12"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"97","13"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"98","14"));
        list.add(new StudentDetailCheckModelClass(R.drawable.chair,"100","15"));

        StudentDetailCheckAdapter studentAdapter = new StudentDetailCheckAdapter(list,this);

        binding.seatsRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        binding.seatsRecyclerView.setAdapter(studentAdapter);

        //Implement RecyclerView and get Value from Firebase and set value
    }
    public void Instances(){
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }
}