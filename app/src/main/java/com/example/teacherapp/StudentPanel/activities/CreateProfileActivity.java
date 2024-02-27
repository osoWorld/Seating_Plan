package com.example.teacherapp.StudentPanel.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.databinding.ActivityCreateProfileBinding;
import com.example.teacherapp.modelClass.Users;
import com.example.teacherapp.teacherPanel.activities.TeacherDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class CreateProfileActivity extends AppCompatActivity {
    private ActivityCreateProfileBinding binding;
    private String currentStatus, roleStatus, uid, profileLink, userEmail, userPassword;
    private Uri imageUri;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private ArrayList<String> department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        Intent intent = getIntent();
        roleStatus = intent.getStringExtra("status");
        userEmail = intent.getStringExtra("stuEmail");
        userPassword = intent.getStringExtra("userPassword");


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        if (auth.getCurrentUser() != null) {
            uid = auth.getCurrentUser().getUid();
        }

        department = new ArrayList<>();
        department.add("Computer Science");
        department.add("Information Technology");
        department.add("Artificial Intelligence");
        department.add("Cyber Security");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, department);
        binding.spinnerDepartment.setAdapter(arrayAdapter);

        binding.spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentStatus = parent.getItemAtPosition(position).toString();
                Toast.makeText(CreateProfileActivity.this, "Selected: " + currentStatus, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.continueProButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String yourName = binding.yourName.getText().toString().trim();
                String yourId = binding.idNumber.getText().toString().trim();

                if (yourName.isEmpty() || yourId.isEmpty()) {
                    Toast.makeText(CreateProfileActivity.this, "Field's cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage(yourId, yourName, userEmail, userPassword, uid);
                }
            }
        });

        binding.mainProfileCreateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                imagePickerActivityResult.launch(galleryIntent);

            }
        });

    }

    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        binding.mainProfileCreateImage.setImageURI(imageUri);
                    }
                }
            }
    );

    private void movingAccordingToRole(String roleStatus) {
        if (roleStatus.equals("Admin")) {
            Intent intent = new Intent(CreateProfileActivity.this, AdminDashboardActivity.class);
            startActivity(intent);
        } else if (roleStatus.equals("Teacher")) {
            Intent intent = new Intent(CreateProfileActivity.this, TeacherDashboardActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(CreateProfileActivity.this, StudentsDashboardActivity.class);
            startActivity(intent);
        }
    }

    private void uploadImage(String userId, String name, String email, String userPassword, String uid) {
        ProgressDialog pd = new ProgressDialog(CreateProfileActivity.this);
        pd.setMessage("Uploading");
        pd.show();
        if (imageUri != null) {
            storageReference = firebaseStorage.getReference();
            StorageReference imageRef = storageReference.child("Profile Images").child(roleStatus).child(uid);

            imageRef.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Image upload successful, now retrieve download URL
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        profileLink = uri.toString();
                        addToDatabase(userId, name, email, userPassword, profileLink, uid);
                        pd.dismiss();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(CreateProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    });
                } else {
                    Toast.makeText(CreateProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }
    }

    private void addToDatabase(String userId, String name, String email, String userPassword, String imageLink, String uid) {
        DatabaseReference myRef = database.getReference("Seating Plan").child("Profile Details");
        Users obj = new Users(userId, name, userPassword, email, roleStatus, currentStatus, imageLink, uid, "Absent", "UnAssigned", false);
        myRef.child(uid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreateProfileActivity.this, "Welcome " + currentStatus, Toast.LENGTH_SHORT).show();
                    movingAccordingToRole(roleStatus);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}