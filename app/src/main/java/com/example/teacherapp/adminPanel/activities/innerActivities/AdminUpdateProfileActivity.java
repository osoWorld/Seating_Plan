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

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.CreateProfileActivity;
import com.example.teacherapp.adminPanel.activities.AdminDashboardActivity;
import com.example.teacherapp.databinding.ActivityAdminUpdateProfileBinding;
import com.example.teacherapp.modelClass.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AdminUpdateProfileActivity extends AppCompatActivity {
    private ActivityAdminUpdateProfileBinding binding;
    private String uid;
    private FirebaseAuth auth;
    private Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String profileLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        getUserData();

        binding.updateAdminProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });
        binding.saveInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.adminNameEd.getText().toString().trim();
                String email = binding.adminEmailED.getText().toString().trim();
                String password = binding.adminPasswordEd.getText().toString().trim();

                updateImg(name,email,password);

            }
        });

    }

    private void getUserData() {
        if (uid != null) {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details").child("Admin").child(uid);
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Users userDetail = snapshot.getValue(Users.class);

                        if (userDetail != null) {

                            String names = userDetail.getUserName();
                            String emails = userDetail.getUserEmail();
                            String passwords = userDetail.getUserPassword();

                            binding.adminNameEd.setText(names);
                            binding.adminEmailED.setText(emails);
                            binding.adminPasswordEd.setText(passwords);

                            Glide.with(getApplicationContext()).load(userDetail.getImageUrl()).placeholder(R.drawable.user_pro).into(binding.updateAdminProfileImg);

                            Toast.makeText(AdminUpdateProfileActivity.this, "User Found", Toast.LENGTH_SHORT).show();

                        } else {
                            // Handle the case where userDetail is null
                            Toast.makeText(AdminUpdateProfileActivity.this, "User details not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AdminUpdateProfileActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateProfile(String name, String email, String password, String imageUri){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Seating Plan").child("Profile Details").child("Admin").child(uid);

        Map<String, Object> updates = new HashMap<>();
        updates.put("userName",name);
        updates.put("userEmail",email);
        updates.put("userPassword",password);
        updates.put("imageUrl",imageUri);

        usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    binding.progressUpdate.setVisibility(View.GONE);
                    Toast.makeText(AdminUpdateProfileActivity.this, "Updated Profile", Toast.LENGTH_SHORT).show();
                    getUserData();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressUpdate.setVisibility(View.GONE);
                Toast.makeText(AdminUpdateProfileActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        binding.updateAdminProfileImg.setImageURI(imageUri);
                    }
                }
            }
    );

    private void updateImg(String name, String email, String password){
        if (imageUri != null) {
            binding.progressUpdate.setVisibility(View.VISIBLE);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            StorageReference imageRef = storageReference.child("Profile Images").child("Admin").child(uid);

            imageRef.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Image upload successful, now retrieve download URL
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        profileLink = uri.toString();

                        updateProfile(name,email,password,profileLink);

                    }).addOnFailureListener(e -> {
                        binding.progressUpdate.setVisibility(View.GONE);
                        Toast.makeText(AdminUpdateProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    binding.progressUpdate.setVisibility(View.GONE);
                    Toast.makeText(AdminUpdateProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminUpdateProfileActivity.this, AdminDashboardActivity.class));
    }
}