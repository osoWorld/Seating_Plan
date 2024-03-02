package com.example.teacherapp.adminPanel.adminFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignSeatsAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentRoom121Binding;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Room121Fragment extends Fragment {
    private FragmentRoom121Binding binding;
    private ArrayList<AssignedSeatModelClass> list;
    private ArrayList<TeacherStudentListModelClass> datalist;
    private TeacherStudentListAdapter adapter;
    private DatabaseReference reference;

    public Room121Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoom121Binding.inflate(inflater,container,false);


         getRoomData();
        return binding.getRoot();
    }
    private void getRoomData() {
        list = new ArrayList<>();
        datalist = new ArrayList<>();

        adapter = new TeacherStudentListAdapter(datalist, requireContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("AssignedRooms").child("121");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                datalist.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    AssignedSeatModelClass model = snapshot1.getValue(AssignedSeatModelClass.class);
                    String uid = model.getUserID();
                    Log.d("userIds",uid);
                    if (uid==null){
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show();

                    }else{
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
                        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    TeacherStudentListModelClass obj = snapshot.getValue(TeacherStudentListModelClass.class);
                                    Log.d("userIds",obj.getUserName());
                                    datalist.add(obj);
                                    binding.progressB.setVisibility(View.GONE);

                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressB.setVisibility(View.GONE);
                            }
                        });
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "FT2"+error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressB.setVisibility(View.GONE);

            }
        });


        binding.assignSeatsRecView.setAdapter(adapter);
        binding.assignSeatsRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}