package com.example.teacherapp.StudentPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.adapterClasses.StudentDetailCheckAdapter;
import com.example.teacherapp.StudentPanel.classes.modelClasses.StudentDetailCheckModelClass;
import com.example.teacherapp.databinding.ActivityStudentDetailCheckBinding;
import com.example.teacherapp.modelClass.AssignDutySheetModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentDetailCheckActivity extends AppCompatActivity {
    private ActivityStudentDetailCheckBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser user;
    String currentuserID,seatnumber,roomnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDetailCheckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));
        // get Data
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();
        getSeatDetails();
        //Implement RecyclerView and get Value from Firebase and set value

    }

   private void getSeatDetails(){
       reference = database.getReference("AssignDuty").child("Teacher");
        if (user!=null){
            currentuserID = user.getUid().toString();
            reference.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        AssignDutySheetModelClass obj = snapshot.getValue(AssignDutySheetModelClass.class);
                        seatnumber = obj.getStudentseatnumber();
                        roomnumber = obj.getUserRoom();
                        binding.roomNumberEd.setText(roomnumber);
                        ShowList();
                    }else{
                        Toast.makeText(StudentDetailCheckActivity.this, "not Assign yet", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(StudentDetailCheckActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }

   }
   private void ShowList(){
       ArrayList<StudentDetailCheckModelClass> list = new ArrayList<>();

       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"85","1"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"86","2"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"87","3"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"88","4"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"89","5"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"90","6"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"91","7"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"92","8"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"93","9"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"94","10"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"95","11"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"96","12"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"97","13"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"98","14"));
       list.add(new StudentDetailCheckModelClass(R.drawable.chair,"100","15"));

       StudentDetailCheckAdapter studentAdapter = new StudentDetailCheckAdapter(list,this,seatnumber);

       binding.seatsRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
       binding.seatsRecyclerView.setAdapter(studentAdapter);
   }
}