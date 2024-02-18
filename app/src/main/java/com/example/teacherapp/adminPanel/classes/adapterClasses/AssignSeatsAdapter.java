package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.activities.StudentAssignSeatsActivity;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;

import java.util.ArrayList;

public class AssignSeatsAdapter extends RecyclerView.Adapter<AssignSeatsAdapter.AssignSeatsViewHolder> {
    private ArrayList<TeacherStudentListModelClass> mList;
    private Context context;

    public AssignSeatsAdapter(ArrayList<TeacherStudentListModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignSeatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_list, parent, false);
        return new AssignSeatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignSeatsViewHolder holder, int position) {
        final TeacherStudentListModelClass data = mList.get(position);

        Glide.with(context).load(data.getImageUrl())
                .placeholder(R.drawable.user_pro)
                .into(holder.teacherStudentImg);

        holder.teacherStudentName.setText(data.getUserName());
        holder.teacherStudentId.setText(data.getUserId());
        holder.teacherStudentRoom.setText(data.getStudentDepartment());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, StudentAssignSeatsActivity.class)
                        .putExtra("studentName", data.getUserName())
                        .putExtra("studentId", data.getUserId())
                        .putExtra("studentDepartment",data.getStudentDepartment()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AssignSeatsViewHolder extends RecyclerView.ViewHolder {
        ImageView teacherStudentImg;
        TextView teacherStudentName, teacherStudentId, teacherStudentRoom;

        public AssignSeatsViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherStudentImg = itemView.findViewById(R.id.studentTeacherListImg);
            teacherStudentName = itemView.findViewById(R.id.studentTeacherName);
            teacherStudentId = itemView.findViewById(R.id.studentTeacherId);
            teacherStudentRoom = itemView.findViewById(R.id.studentTeacherRoomNo);

        }
    }
}
