package com.example.teacherapp.adminPanel.activities.innerActivities.AdminStudentListFragment;

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
import com.example.teacherapp.databinding.FragmentAiStudentlistBinding;
import com.example.teacherapp.databinding.FragmentAiTeacherlistBinding;
import com.example.teacherapp.databinding.FragmentBscsTeacherlistBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BscsStudentListFragment extends Fragment {

   private FragmentBscsTeacherlistBinding binding;
    private ArrayList<TeacherStudentListModelClass> list;
    private DatabaseReference reference;
    private TeacherStudentListAdapter adapter;

    public BscsStudentListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBscsTeacherlistBinding.inflate(inflater,container,false);
        list = new ArrayList<>();

        adapter = new TeacherStudentListAdapter(list, requireActivity());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");

        reference.orderByChild("currentStatus").equalTo("Student")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        if (snapshot.exists()){
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                TeacherStudentListModelClass model = snapshot1.getValue(TeacherStudentListModelClass.class);
                                String department = model.getStudentDepartment();
                                if (department.equals("Computer Science")) {
                                    list.add(model);
                                }
                                Log.d("UserNam", model.getUserName() + " UserId: " + model.getUid());
                            }
                            binding.progressB.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }else{
                            binding.progressB.setVisibility(View.GONE);
                            Toast.makeText(requireActivity(), "No Student Found", Toast.LENGTH_SHORT).show();
                        }

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