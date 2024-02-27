package com.example.teacherapp.adminPanel.activities.innerActivities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.databinding.ActivityUploadDateSheetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class UploadDateSheetActivity extends AppCompatActivity {
    private ActivityUploadDateSheetBinding binding;
    private Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    String profileLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadDateSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        binding.galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });

        binding.uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department = binding.departmentED.getText().toString().trim();
                String examType = binding.examTypeED.getText().toString().trim();

                if (department.isEmpty() || examType.isEmpty()) {
                    Toast.makeText(UploadDateSheetActivity.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    updateImg(department, examType);
                }
            }
        });

    }

    private void updateProfile(String department, String examType, String imageUri) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef =
                database.getReference("Seating Plan")
                        .child(department)
                        .child(examType);

        Map<String, Object> updates = new HashMap<>();
        updates.put("department", department);
        updates.put("examType", examType);
        updates.put("imageUrl", imageUri);

        usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);
                    //                   Toast.makeText(getContext(), "Updated Profile", Toast.LENGTH_SHORT).show();
//                    getUserData();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);
                //               Toast.makeText(getContext(), "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateImg(String department, String examType) {
        if (imageUri != null) {
            binding.progressBar.setVisibility(View.VISIBLE);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            StorageReference imageRef = storageReference.child("Date Sheet").child(department).child(examType);

            imageRef.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Image upload successful, now retrieve download URL
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        profileLink = uri.toString();

                        updateProfile(department, examType, profileLink);

                    }).addOnFailureListener(e -> {
                        binding.progressBar.setVisibility(View.GONE);
                        //                       Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    binding.progressBar.setVisibility(View.GONE);
//                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        binding.dateSheetImage.setImageURI(imageUri);
                    }
                }
            }
    );

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UploadDateSheetActivity.this, AdminDashboardActivity.class));
    }
}