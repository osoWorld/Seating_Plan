package com.example.teacherapp.adminPanel.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.SignupActivity;
import com.example.teacherapp.adminPanel.classes.adapterClasses.AdminDashboardAdapter;
import com.example.teacherapp.adminPanel.classes.modelClasses.AdminDashboardItemsModelClass;
import com.example.teacherapp.databinding.ActivityAdminDashboardBinding;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {
    private ActivityAdminDashboardBinding binding;
    private ArrayList<AdminDashboardItemsModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.fav_blue));

        list = new ArrayList<>();
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_list_alt_24, "Student List", 1));
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_list_alt_24, "Teacher List", 2));
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_file_upload_24, "Upload Date Sheet", 3));
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_file_upload_24, "Upload Duty Sheet", 4));
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_chair_alt_24,"Assign Seats",5));
        list.add(new AdminDashboardItemsModelClass(R.drawable.baseline_home_filled_24,"Assign Rooms",6));
        list.add(new AdminDashboardItemsModelClass(R.drawable.user_my,"Update Profile",7));
        list.add(new AdminDashboardItemsModelClass(R.drawable.log_out,"Log out",8));

        AdminDashboardAdapter adapter = new AdminDashboardAdapter(list,this);

        binding.adminDashboardRecView.setAdapter(adapter);
        binding.adminDashboardRecView.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboardActivity.this);
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