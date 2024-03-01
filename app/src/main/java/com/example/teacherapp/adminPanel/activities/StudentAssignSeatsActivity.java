package com.example.teacherapp.adminPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.adminFragment.AssignRoomsFragment;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AssignRoomStudentAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.databinding.ActivityStudentAssignSeatsBinding;
import com.example.teacherapp.modelClass.AssignedSeatModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentAssignSeatsActivity extends AppCompatActivity implements AssignRoomStudentAdapter.OnItemClickListener {
    private ActivityStudentAssignSeatsBinding binding;
    private ArrayList<AssignRoomModelClass> list;
    ArrayList<TeacherStudentListModelClass> receivedItems;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference refAssingroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentAssignSeatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        list = new ArrayList<>();

        list.add(new AssignRoomModelClass(R.drawable.room_door,"120"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"124"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"128"));
        list.add(new AssignRoomModelClass(R.drawable.room_door,"132"));

        AssignRoomStudentAdapter adapter = new AssignRoomStudentAdapter(list,this,this);

        binding.assignSeatRecView.setAdapter(adapter);
        binding.assignSeatRecView.setLayoutManager(new GridLayoutManager(this,2));
        // receiving the students
        receivedItems = (ArrayList<TeacherStudentListModelClass>) getIntent().getSerializableExtra("selectedItems");
//        for (TeacherStudentListModelClass list :receivedItems){
//            Toast.makeText(this, ""+list.getUid(), Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onItemClick(AssignRoomModelClass roomData) {
        String roomNum = roomData.getRoomName();
         Assigned(roomNum);
    }
    private void Assigned(String roomNum) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Seating Plan");
        refAssingroom = database.getReference("AssignedRooms").child(roomNum);

        DatabaseReference profileDetailsRef = reference.child("Profile Details");

        for (TeacherStudentListModelClass selected : receivedItems) {
            String uid = selected.getUid();

            profileDetailsRef.child(uid).child("seatingStatus").setValue("Assigned")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                assignedRoom(uid, roomNum);
                            } else {
                                Toast.makeText(StudentAssignSeatsActivity.this, "Failed to update seating status", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(StudentAssignSeatsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void assignedRoom(String userID, String roomNum) {
        AssignedSeatModelClass obj = new AssignedSeatModelClass(userID, roomNum);

        refAssingroom.child(userID).setValue(obj)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(StudentAssignSeatsActivity.this,AdminDashboardActivity.class));
                            Toast.makeText(StudentAssignSeatsActivity.this, "Successfully Assigned", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StudentAssignSeatsActivity.this, "Failed to assign room", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StudentAssignSeatsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

