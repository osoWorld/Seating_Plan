package com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.FragmentBsItStudentlistBinding;
import com.example.teacherapp.databinding.FragmentCsStudentListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CsStudentListFragment extends Fragment {

    private FragmentCsStudentListBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    private DatabaseReference reference;
    private TeacherStudentListAdapter adapter;
    public CsStudentListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCsStudentListBinding.inflate(inflater,container,false);
        list = new ArrayList<>();

        adapter = new TeacherStudentListAdapter(list, requireActivity());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");

        reference.orderByChild("currentStatus").equalTo("Student")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            TeacherStudentListModelClass model = snapshot1.getValue(TeacherStudentListModelClass.class);
                            String department = model.getStudentDepartment();
                            if (department.equals("Cyber Security")) {
                                list.add(model);
                            }
                            Log.d("UserNam", model.getUserName() + " UserId: " + model.getUid());
                        }
                        binding.progressB.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled
                    }
                });

        binding.studentListAdminRecyclerView.setAdapter(adapter);
        binding.studentListAdminRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        return  binding.getRoot();
    }
}