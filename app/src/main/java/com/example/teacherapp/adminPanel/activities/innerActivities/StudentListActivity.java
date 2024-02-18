package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private ActivityStudentListBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    private DatabaseReference reference;
    private TeacherStudentListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        adapter = new TeacherStudentListAdapter(list,this);
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details").child("Student");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    TeacherStudentListModelClass model = snapshot1.getValue(TeacherStudentListModelClass.class);
                    if (model != null) {
                        list.add(model);
                        binding.progressB.setVisibility(View.GONE);
                    }
                    Log.d("UserNam",model.getUserName()+" UserId: "+model.getUid());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.studentListAdminRecyclerView.setAdapter(adapter);
        binding.studentListAdminRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}