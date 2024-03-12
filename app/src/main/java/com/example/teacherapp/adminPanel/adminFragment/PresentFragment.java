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
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentPresentBinding;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.example.teacherapp.teacherPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PresentFragment extends Fragment {
    private FragmentPresentBinding binding;
    private ArrayList<StudentAttendanceModelClass> list;
    private DatabaseReference reference;
    private StudentAttendanceAdapter adapter;
    String roomdata;
    public PresentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBinding.inflate(inflater,container,false);
//        Bundle args = getArguments();
//        if (args != null) {
//            String roomData = args.getString("RoomData", "Default value if key not found");
//            Toast.makeText(requireContext(), ""+roomData, Toast.LENGTH_SHORT).show();
//        }
        getRoomData();


        return binding.getRoot();
    }
    private void getRoomData() {
        list = new ArrayList<>();

        adapter = new StudentAttendanceAdapter(list, requireContext());
        binding.progressBB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("AssignedRooms").child("121");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
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
                                    StudentAttendanceModelClass obj = snapshot.getValue(StudentAttendanceModelClass.class);
                                    String status = obj.getStudentAttendanceStatus();
                                    Log.d("userIds",obj.getUserName());
                                    if (status.equals("Present")){
                                        list.add(obj);
                                        binding.progressBB.setVisibility(View.GONE);

                                        adapter.notifyDataSetChanged();
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressBB.setVisibility(View.GONE);
                            }
                        });

                        binding.progressBB.setVisibility(View.GONE);
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "FT2"+error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressBB.setVisibility(View.GONE);

            }
        });


        binding.presentRecView.setAdapter(adapter);
        binding.presentRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

}