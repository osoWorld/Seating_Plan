package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.modelClass.ViewDutySheetModelClass;

import java.util.ArrayList;

public class ViewDutyStudentListAdapter extends RecyclerView.Adapter<ViewDutyStudentListAdapter.TeacherStudentListViewHolder> {
    private ArrayList<ViewDutySheetModelClass> mList;
    private Context context;

    public ViewDutyStudentListAdapter(ArrayList<ViewDutySheetModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duty_cell_layout, parent, false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final ViewDutySheetModelClass data = mList.get(position);

//        Glide.with(context).load(data.getImageUrl())
//                .placeholder(R.drawable.user_pro)
//                .into(holder.teacherStudentImg);
//
        holder.teacherStudentname.setText(data.getUsername());
        holder.teacherStudentdepart.setText(data.getUserdepartment());
        holder.teacherStudentseat.setText(data.getSeatnumber());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder {
        ImageView teacherStudentImg;
        TextView teacherStudentseat, teacherStudentname, teacherStudentdepart;

        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

//            teacherStudentImg = itemView.findViewById(R.id.studentTeacherListImg);
            teacherStudentseat = itemView.findViewById(R.id.seatNumberTextView);
            teacherStudentname = itemView.findViewById(R.id.usernameTextView);
            teacherStudentdepart = itemView.findViewById(R.id.departmentTextView);
        }
    }
}
