package com.example.teacherapp.teacherPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentAttendanceBinding;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
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

public class StudentAttendanceActivity extends AppCompatActivity {
    private ActivityStudentAttendanceBinding binding;
    private ArrayList<StudentAttendanceModelClass> list;
    private DatabaseReference reference;
    private StudentAttendanceAdapter adapter;
    FirebaseAuth auth;
    FirebaseUser user;
    String roomData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        list = new ArrayList<>();

        adapter = new StudentAttendanceAdapter(list, this);
        binding.progressB.setVisibility(View.VISIBLE);

        binding.submitAttendanceSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadAttendance();
                adapter.notifyDataSetChanged();


            }
        });
        //current login Id
        auth = FirebaseAuth.getInstance();
        if (user!=null){
            String currentloginID = user.getUid().toString();
            reference = FirebaseDatabase.getInstance().getReference(
                    "TeacherAssignDuty").child("Details");

            reference.child(currentloginID).addValueEventListener(new ValueEventListener() {
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
                                        Log.d("userIds",obj.getUserName());
                                        list.add(obj);
                                        binding.progressB.setVisibility(View.GONE);

                                        adapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(getApplicationContext(), "FT"+error.getMessage(), Toast.LENGTH_SHORT).show();
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
            binding.studentAttendanceSheetRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        }


    }

    private void uploadAttendance() {

        ProgressDialog pd = new ProgressDialog(StudentAttendanceActivity.this);
        pd.setMessage("Uploading");
        pd.setTitle("Upload");
        pd.setCancelable(false);
        pd.show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        reference = FirebaseDatabase.getInstance().getReference("Attendance Seating Plan")
                .child("Attendance")
                .child(roomData);


        for (StudentAttendanceModelClass student : list) {
            String uid = student.getUid();
            String status = student.getStudentAttendanceStatus();

            StudentAttendanceModelClass obj = new StudentAttendanceModelClass(
                    student.getUserId(), student.getUserName(), student.getUserEmail(),
                    student.getStudentDepartment(), student.getImageUrl(), uid, status);
            String key = reference.push().getKey().toString();
            reference.child(key).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(StudentAttendanceActivity.this, "Attendance Uploaded", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(StudentAttendanceActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });

            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StudentAttendanceActivity.this,TeacherDashboardActivity.class));
    }
}