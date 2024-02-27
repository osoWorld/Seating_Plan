package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.activities.LoginActivity;
import com.example.teacherapp.StudentPanel.activities.SignupActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminLogoutActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.AdminUpdateProfileActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.AssignRoomsActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.AssignSeatsActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.StudentListActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.TeacherListActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.UploadDateSheetActivity;
import com.example.teacherapp.adminPanel.activities.innerActivities.UploadDutySheetActivity;
import com.example.teacherapp.adminPanel.classes.modelClasses.AdminDashboardItemsModelClass;
import com.example.teacherapp.sharedPrefrences.PrefManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminDashboardAdapter extends RecyclerView.Adapter<AdminDashboardAdapter.AdminDashboardViewHolder> {
    private ArrayList<AdminDashboardItemsModelClass> mList;
    private Context context;

    public AdminDashboardAdapter(ArrayList<AdminDashboardItemsModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminDashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_items_layout,parent,false);
        return new AdminDashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDashboardViewHolder holder, int position) {
        final AdminDashboardItemsModelClass data = mList.get(position);
        holder.itemIcon.setImageResource(data.getItemIcon());
        holder.itemName.setText(data.getItemName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = data.getItemPosition();

                if (id == 1){
                    context.startActivity(new Intent(context, StudentListActivity.class));
                } else if (id == 2) {
                    context.startActivity(new Intent(context, TeacherListActivity.class));
                } else if (id == 3) {
                    context.startActivity(new Intent(context, UploadDateSheetActivity.class));
                } else if (id == 4) {
                    context.startActivity(new Intent(context, UploadDutySheetActivity.class));
                } else if (id == 5) {
                    context.startActivity(new Intent(context, AssignSeatsActivity.class));
                } else if (id == 6) {
                    context.startActivity(new Intent(context, AssignRoomsActivity.class));
                } else if (id == 7) {
                    context.startActivity(new Intent(context, AdminUpdateProfileActivity.class));
                } else if (id == 8) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you sure want to logout ?");
                    builder.setTitle("Logout !");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        PrefManager prefManager = new PrefManager(context);
                        prefManager.setCurrentstatus("");
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AdminDashboardViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;
        TextView itemName;
        public AdminDashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            itemIcon = itemView.findViewById(R.id.adminDashImg);
            itemName = itemView.findViewById(R.id.adminDashText);
        }
    }
}
