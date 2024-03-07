package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.adapterClasses.TeacherStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.adapterClasses.ViewDutyStudentListAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityAdminUpdateProfileBinding;
import com.example.teacherapp.databinding.ActivityAssignDutyToteacherBinding;
import com.example.teacherapp.modelClass.AssignDutySheetModelClass;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.example.teacherapp.modelClass.DutyDetailsModeClass;
import com.example.teacherapp.modelClass.ViewDutySheetModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class AssignDutyTOTeacherActivity extends AppCompatActivity {
    private ActivityAssignDutyToteacherBinding binding;
    private ArrayList<AssignedSeatModelClass> list;
    private ArrayList<ViewDutySheetModelClass> datalist;
    private ViewDutyStudentListAdapter adapter;
    private ArrayList<String> teacherlist;
    private DatabaseReference reference;
    ArrayAdapter<String> teacheradapter;
    String roomdata,selectedteacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignDutyToteacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        roomdata = getIntent().getStringExtra("roomkey");
        if (roomdata != null) {
            getRoomData(roomdata);
        }
        teacherlist = new ArrayList<>();
        teacheradapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, teacherlist);

        // Set the adapter to AutoCompleteTextView
        binding.autoCompleteTextView.setAdapter(teacheradapter);
        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedteacher = parent.getItemAtPosition(position).toString();
                Toast.makeText(AssignDutyTOTeacherActivity.this, "Selected"+selectedteacher, Toast.LENGTH_SHORT).show();
            }
        });
        GetTeacherlist();
        // assigndutybtn
        binding.assignDutyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssignDutyToTeacher();
            }
        });

    }

    private void getRoomData(String roonnum) {
        list = new ArrayList<>();
        datalist = new ArrayList<>();

        adapter = new ViewDutyStudentListAdapter(datalist, getApplicationContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("AssignedRooms").child(roonnum);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                datalist.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    AssignedSeatModelClass model = snapshot1.getValue(AssignedSeatModelClass.class);
                    String uid = model.getUserID();
                    String seatnumber = model.getSeatnumber();
                    if (uid == null) {
                        Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();

                    } else {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
                        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    TeacherStudentListModelClass obj = snapshot.getValue(TeacherStudentListModelClass.class);
                                    String logMessage = "Log 1: " + obj.getUserName() + ", Log 2: " + obj.getStudentDepartment();
                                    Log.d("TAG", logMessage);
                                    ViewDutySheetModelClass obj2 = new ViewDutySheetModelClass(obj.getUid(),seatnumber,obj.getUserName(),obj.getStudentDepartment());
                                    datalist.add(obj2);

                                    binding.progressB.setVisibility(View.GONE);

                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getApplicationContext(), "FT" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressB.setVisibility(View.GONE);
                            }
                        });
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "FT2" + error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressB.setVisibility(View.GONE);

            }
        });


        binding.assignSeatsRecView.setAdapter(adapter);
        binding.assignSeatsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void GetTeacherlist() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        TeacherStudentListModelClass obj = snapshot1.getValue(TeacherStudentListModelClass.class);
                        String status = obj.getCurrentStatus();
                        if (status.equals("Teacher")) {
                            teacherlist.add(obj.getUserName());
                        }

                    }


//                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(AssignDutyTOTeacherActivity.this, "not fount", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "FT" + error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressB.setVisibility(View.GONE);
            }
        });
    }
    private void AssignDutyToTeacher() {
        // Check if a teacher is selected
        if (selectedteacher == null || selectedteacher.isEmpty()) {
            Toast.makeText(this, "Please select a teacher", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if datalist is not empty
        if (datalist.isEmpty()) {
            Toast.makeText(this, "No data to assign", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference teacherRef = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details");
        teacherRef.orderByChild("userName").equalTo(selectedteacher).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot teacherSnapshot : snapshot.getChildren()) {
                        // Get the UID of the selected teacher
                        String selectedTeacherUid = teacherSnapshot.getKey();

                        // Save the assignment to Firebase using the UID
                        DatabaseReference assignmentRef = FirebaseDatabase.getInstance().getReference("AssignDuty").child("Teacher");
                        DatabaseReference assigndutyDetailstRef = FirebaseDatabase.getInstance().getReference("TeacherAssignDuty").child("Details");

                        for (ViewDutySheetModelClass data : datalist) {
                            String userID = data.getUserID();
                            String seatnumber = data.getSeatnumber();
                            DutyDetailsModeClass obj3 = new DutyDetailsModeClass(selectedTeacherUid, roomdata, userID,seatnumber);

                            // Generate a unique key for each assignment
                            String key = assigndutyDetailstRef.push().getKey();

                            AssignDutySheetModelClass obj2 = new AssignDutySheetModelClass(userID, roomdata, selectedteacher, selectedTeacherUid,seatnumber);

                            assignmentRef.child(userID).setValue(obj2)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Use the unique key for each record in the details reference
                                                assigndutyDetailstRef.child(selectedTeacherUid).child(key).setValue(obj3);
                                                sendMessage();
                                                Toast.makeText(AssignDutyTOTeacherActivity.this, "Duty assigned successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(AssignDutyTOTeacherActivity.this, "Failed to assign duty: " + task.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                    }
                } else {
                    Toast.makeText(AssignDutyTOTeacherActivity.this, "Teacher not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AssignDutyTOTeacherActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendMessage(){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("03117914669", null, "Duty Sheet is Ready", null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed to send.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("Exception_____________"+e.getMessage());
        }
    }



}



