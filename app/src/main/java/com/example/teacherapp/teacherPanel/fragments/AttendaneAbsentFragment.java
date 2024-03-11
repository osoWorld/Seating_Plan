package com.example.teacherapp.teacherPanel.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.FragmentAttendancePresentStudentBinding;
import com.example.teacherapp.databinding.FragmentAttendaneAbsentBinding;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
import com.example.teacherapp.teacherPanel.activities.StudentAttendanceActivity;
import com.example.teacherapp.teacherPanel.activities.TeacherDashboardActivity;
import com.example.teacherapp.teacherPanel.classes.adapterClasses.StudentAttendanceAdapter;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AttendaneAbsentFragment extends Fragment {


   private FragmentAttendaneAbsentBinding binding;
    private ArrayList<StudentAttendanceModelClass> list;
    private DatabaseReference reference;
    private StudentAttendanceAdapter adapter;

    String roomData,ID;
    public AttendaneAbsentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAttendaneAbsentBinding.inflate(inflater,container,false);

//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        list = new ArrayList<>();

        adapter = new StudentAttendanceAdapter(list, requireContext());
        binding.progressB.setVisibility(View.VISIBLE);

        binding.submitAttendanceSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadAttendance();
                adapter.notifyDataSetChanged();


            }
        });
        //current login Id
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            getData(userId);

            // Now you have the current user's ID (userId)
        } else {
            Log.d("idnull", "null");

            // User is not logged in
        }
        return binding.getRoot();
    }
    private void getData(String uid){

        reference = FirebaseDatabase.getInstance().getReference(
                "TeacherAssignDuty").child("Details");

        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DutyDetailsModeClass model = snapshot1.getValue(DutyDetailsModeClass.class);
                    String userID  = model.getStudentID();
                    roomData = model.getRoomData();


                    if (userID != null) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
                        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    StudentAttendanceModelClass obj = snapshot.getValue(StudentAttendanceModelClass.class);
                                    String status = obj.getStudentAttendanceStatus();
                                    Log.d("userIds",obj.getUserName());
                                    if (status.equals("Absent")){
                                        list.add(obj);
                                        binding.progressB.setVisibility(View.GONE);

                                        adapter.notifyDataSetChanged();
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(requireContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressB.setVisibility(View.GONE);
                            }
                        });

                        binding.progressB.setVisibility(View.GONE);

                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.studentAttendanceSheetRecyclerView.setAdapter(adapter);
        binding.studentAttendanceSheetRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

    }




    private void uploadAttendance() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        ArrayList<StudentAttendanceModelClass> stdlist =adapter.getSelected();
        if (stdlist.isEmpty()){
            Toast.makeText(requireContext(), "no Selected Items", Toast.LENGTH_SHORT).show();
        }else{
            ProgressDialog pd = new ProgressDialog(requireContext());
            pd.setMessage("Uploading");
            pd.setTitle("Upload");
            pd.setCancelable(false);
            pd.show();
            for (StudentAttendanceModelClass student : stdlist) {
                ID = student.getUid();
                if (ID!=null){
                    Log.d( "uploadAttendance: ",ID);
                    reference = FirebaseDatabase.getInstance().getReference("Seating Plan")
                            .child("Profile Details").child(ID);
                    reference.child(
                            "studentAttendanceStatus").setValue("Present").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(requireContext(), "Attendance Uploaded", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });

                    adapter.notifyDataSetChanged();

                }

            }
        }


    }


//    public void onBackPressed() {
//        startActivity(new Intent(requireContext(), TeacherDashboardActivity.class));
//    }
}