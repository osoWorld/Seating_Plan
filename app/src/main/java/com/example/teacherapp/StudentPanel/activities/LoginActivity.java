package com.example.teacherapp.StudentPanel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.databinding.ActivityLoginBinding;
import com.example.teacherapp.modelClass.Users;
import com.example.teacherapp.sharedPrefrences.PrefManager;
import com.example.teacherapp.teacherPanel.activities.TeacherDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;

    private String email, password;
    private PrefManager prefManager;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.white));

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Seating Plan").child("Profile Details");


        prefManager = new PrefManager(this);

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = binding.email.getText().toString().trim();
                 password = binding.password.getText().toString().trim();


//                if (email.isEmpty() && password.isEmpty()){
//                    binding.email.setError("Fields can't be empty");
//                    binding.password.setError("Fields can't be empty");
////                } else if (email.isEmpty()){
////                    binding.email.setError("Field can't be empty");
////                } else if (password.isEmpty()) {
////                    binding.password.setError("Field can't be empty");
//                } else {
//                    binding.progressBarLogin.setVisibility(View.VISIBLE);
//
//                }

                SignIn(email, password);
            }
        });

        binding.registerNowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }

    private void SignIn(String email,String pass){
        binding.progressBarLogin.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                String current_user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId=currentUser.getUid();
                if (task.isSuccessful()){
//                    System.out.println("userss__________________________"+currentUser);
                    reference.child(userId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Users obj = snapshot.getValue(Users.class);
                                String status = obj.getCurrentStatus();
                                if (status.equals("Admin")){
                                    prefManager.setCurrentstatus("Admin");
                                    prefManager.setUserID(userId);
                                    Intent intent=new Intent(getApplicationContext(), AdminDashboardActivity.class);
                                    startActivity(intent);
                                    binding.progressBarLogin.setVisibility(View.GONE);
                                    //next proccess
                                }else if (status.equals("Teacher")) {
                                    prefManager.setCurrentstatus("Teacher");
                                    prefManager.setUserID(userId);
                                    Intent intent = new Intent(getApplicationContext(), TeacherDashboardActivity.class);
                                    startActivity(intent);
                                    binding.progressBarLogin.setVisibility(View.GONE);
                                    // next 2nd step
                                } else if (status.equals("Student")) {
                                    prefManager.setCurrentstatus("Student");
                                    prefManager.setUserID(userId);
                                    Intent intent = new Intent(getApplicationContext(), StudentsDashboardActivity.class);
                                    startActivity(intent);
                                    binding.progressBarLogin.setVisibility(View.GONE);
                                }
                            }

                            }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            System.out.println("error________________________"+error.getMessage());
                            binding.progressBarLogin.setVisibility(View.GONE);
                        }
                    });

                }
                else{

                    binding.progressBarLogin.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressBarLogin.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}