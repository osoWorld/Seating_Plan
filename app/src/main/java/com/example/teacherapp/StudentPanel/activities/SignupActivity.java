package com.example.teacherapp.StudentPanel.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.ActivitySignupBinding;
import com.example.teacherapp.modelClass.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String currentStatus;
    private String name, email, password, confirmPassword;
    Drawable googleImg;
    SpannableString spannableString;
    FirebaseDatabase database;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 20;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.white));

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        spannableString = new SpannableString("    Sign up with Google");

        googleImg = getResources().getDrawable(R.drawable.google);
        googleImg.setBounds(0,0,googleImg.getIntrinsicWidth(),googleImg.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(googleImg, ImageSpan.ALIGN_BASELINE);

        spannableString.setSpan(imageSpan,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        binding.googleButton.setText(spannableString);

         arrayList = new ArrayList<>();
        arrayList.add("Student");
        arrayList.add("Teacher");
        arrayList.add("Admin");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);
        binding.spinner.setAdapter(arrayAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentStatus = parent.getItemAtPosition(position).toString();
                Toast.makeText(SignupActivity.this, "Selected: " + currentStatus, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 name = binding.fullName.getText().toString().trim();
                 email = binding.email.getText().toString().trim();
                 password = binding.password.getText().toString().trim();
                 confirmPassword = binding.confirmPassword.getText().toString().trim();

//                if (name.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
//                    binding.fullName.setError("Field can't be empty");
//                    binding.email.setError("Field can't be empty");
//                    binding.password.setError("Field can't be empty");
//                    binding.confirmPassword.setError("Field can't be empty");
////                } else if (name.isEmpty()) {
////                    binding.fullName.setError("Field can't be empty");
////                } else if (email.isEmpty() || !email.contains(".com")) {
////                    binding.email.setError("Invalid email");
////                } else if (password.isEmpty()) {
////                    binding.password.setError("Field can't be empty");
////                } else if (password.length() < 6) {
////                    binding.password.setError("Password strength is weak");
////                } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
////                    binding.confirmPassword.setError("Invalid password");
//                } else {
//
//
//                    binding.progressBar.setVisibility(View.VISIBLE);
//                }

                signInWIthFirebase(email,password);
            }
        });
        binding.loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
        binding.googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                signInWithGoogle();
            }
        });
    }

    private void signInWIthFirebase(String email, String password) {
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    FirebaseUser user = auth.getCurrentUser();
//                    String uid = user.getUid();
//
//                    addToDatabase(uid,name,email,password);
//                    binding.progressBar.setVisibility(View.GONE);
//                }
//            }
//        });

        startActivity(new Intent(SignupActivity.this, CreateProfileActivity.class).putExtra("status",currentStatus));

    }

    private void addToDatabase(String userId, String name, String email, String userPassword) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Seating Plan").child("Account Details");
        Users obj=new Users(userId,name, userPassword,email,currentStatus);
        myRef.child(userId).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("318506129231-kkgj83ut6gr7q0d5tmtqe94266rkv5gd.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
            @Override
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                if (task.isSuccessful()){
                    GoogleSignInAccount account = task.getResult();
                }
                else {
//                    Toast.makeText(SignupActivity.this, "Token Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();

                    HashMap<String, Object> obj = new HashMap<>();
                    obj.put("id",user.getUid());
                    obj.put("name",user.getDisplayName());
                    obj.put("email", user.getEmail());

                    database.getReference("Seating Plan").child("Account Details").child(user.getUid()).setValue(obj);
                    Toast.makeText(SignupActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(SignupActivity.this, StudentDashboardActivity.class));
                }
                else{
                    Toast.makeText(SignupActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finishAffinity();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}