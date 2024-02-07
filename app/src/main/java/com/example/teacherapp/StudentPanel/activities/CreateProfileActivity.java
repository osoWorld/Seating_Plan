package com.example.teacherapp.StudentPanel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.teacherapp.R;
import com.example.teacherapp.databinding.ActivityCreateProfileBinding;

import java.util.ArrayList;

public class CreateProfileActivity extends AppCompatActivity {
    private ActivityCreateProfileBinding binding;
    private String currentStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Computer Science");
        arrayList.add("Information Technology");
        arrayList.add("Artificial Intelligence");
        arrayList.add("Cyber Security");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);
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
                Intent intent = new Intent(CreateProfileActivity.this,StudentsDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}