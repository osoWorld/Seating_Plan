package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.example.teacherapp.modelClass.ViewDutySheetModelClass;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignDutyToteacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String roomdata = getIntent().getStringExtra("roomkey");
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
                String selected = parent.getItemAtPosition(position).toString();
                Toast.makeText(AssignDutyTOTeacherActivity.this, "Selected"+selected, Toast.LENGTH_SHORT).show();
            }
        });
        GetTeacherlist();

    }

    private void getRoomData(String roonnum) {
        list = new ArrayList<>();
        datalist = new ArrayList<>();

        adapter = new ViewDutyStudentListAdapter(datalist, getApplicationContext());
        binding.progressB.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("AssignedRooms").child("124");

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
                                    ViewDutySheetModelClass obj2 = new ViewDutySheetModelClass("1", obj.getUserName(), obj.getStudentDepartment());
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
                        if (status.equals("Admin")) {
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



}