package com.example.teacherapp.teacherPanel.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.LoginActivity;
import com.example.teacherapp.StudentPanel.activities.SignupActivity;
import com.example.teacherapp.databinding.FragmentTeacherUpdateProfileBinding;
import com.example.teacherapp.modelClass.Users;
import com.example.teacherapp.sharedPrefrences.PrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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

public class TeacherUpdateProfileFragment extends Fragment {
    private FragmentTeacherUpdateProfileBinding binding;
    private String uid;
    private FirebaseAuth auth;
    private Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String profileLink;
    public TeacherUpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherUpdateProfileBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        getUserData();

        binding.teacherProfileImage.setOnClickListener(new View.OnClickListener() {
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
                String name = binding.teacherNameEd.getText().toString().trim();
                String email = binding.teacherEmailEd.getText().toString().trim();
                String password = binding.teacherPassED.getText().toString().trim();

                updateImg(name, email, password);

            }
        });

        binding.teacherLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefManager = new PrefManager(getContext());
                prefManager.setCurrentstatus("");
                auth.signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return binding.getRoot();
    }

    private void getUserData() {
        if (uid != null) {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Seating Plan").child("Profile Details").child(uid);
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Users userDetail = snapshot.getValue(Users.class);

                        if (userDetail != null) {

                            String names = userDetail.getUserName();
                            String emails = userDetail.getUserEmail();
                            String passwords = userDetail.getUserPassword();

                            binding.teacherNameEd.setText(names);
                            binding.teacherEmailEd.setText(emails);
                            binding.teacherPassED.setText(passwords);

                            Glide.with(requireContext()).load(userDetail.getImageUrl()).placeholder(R.drawable.user_pro).into(binding.teacherProfileImage);

                            //                          Toast.makeText(getContext(), "User Found", Toast.LENGTH_SHORT).show();

                        } else {
                            // Handle the case where userDetail is null
                            //                          Toast.makeText(getContext(), "User details not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //                 Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateProfile(String name, String email, String password, String imageUri) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Seating Plan").child("Profile Details").child(uid);

        Map<String, Object> updates = new HashMap<>();
        updates.put("userName", name);
        updates.put("userEmail", email);
        updates.put("userPassword", password);
        updates.put("imageUrl", imageUri);

        usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    binding.progressUpdate.setVisibility(View.GONE);
                    //                   Toast.makeText(getContext(), "Updated Profile", Toast.LENGTH_SHORT).show();
                    getUserData();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressUpdate.setVisibility(View.GONE);
                //               Toast.makeText(getContext(), "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        imageUri = result.getData().getData();
                        binding.teacherProfileImage.setImageURI(imageUri);
                    }
                }
            }
    );

    private void updateImg(String name, String email, String password) {
        if (imageUri != null) {
            binding.progressUpdate.setVisibility(View.VISIBLE);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            StorageReference imageRef = storageReference.child("Profile Images").child(uid);

            imageRef.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Image upload successful, now retrieve download URL
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        profileLink = uri.toString();

                        updateProfile(name, email, password, profileLink);

                    }).addOnFailureListener(e -> {
                        binding.progressUpdate.setVisibility(View.GONE);
                        //                       Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    binding.progressUpdate.setVisibility(View.GONE);
//                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}