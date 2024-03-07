package com.example.teacherapp.StudentPanel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.adapterClasses.TeachersListAdapter;
import com.example.teacherapp.StudentPanel.classes.modelClasses.TeachersListModelClass;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.modelClass.AssignDutySheetModelClass;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeachersListFragment extends Fragment {
    private com.example.teacherapp.databinding.FragmentTeachersListBinding binding;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;

    public TeachersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.example.teacherapp.databinding.FragmentTeachersListBinding.inflate(inflater,container,false);
        GetData();
        return binding.getRoot();
    }
    private void GetData(){
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user!=null){
            String userID = user.getUid().toString();
            Log.d( "GetData: ",userID);
            reference = FirebaseDatabase.getInstance().getReference("AssignDuty").child("Teacher");
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                      if (snapshot.exists()){
                              AssignDutySheetModelClass model = snapshot.getValue(AssignDutySheetModelClass.class);
                              String uid = model.getTeacherID();
                              String roomnumber= model.getUserRoom();
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
                                              binding.blockID.setText(obj.getStudentDepartment());
                                              binding.roomID.setText(roomnumber);
                                              binding.teachernameID.setText(obj.getUserName());
                                              Glide.with(requireContext()).load(obj.getImageUrl()).into(binding.circleImageView2);
                                              binding.progressB.setVisibility(View.GONE);

                                          }
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError error) {
                                          Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                          binding.progressB.setVisibility(View.GONE);
                                      }
                                  });


                          }
                      }else{
                          Toast.makeText(requireContext(), "duty not Assign yet", Toast.LENGTH_SHORT).show();
                      }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(requireContext(), "FT2"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.progressB.setVisibility(View.GONE);

                }
            });

        }else{
            Toast.makeText(requireContext(), "duty not Assign yet", Toast.LENGTH_SHORT).show();
        }

    }
}