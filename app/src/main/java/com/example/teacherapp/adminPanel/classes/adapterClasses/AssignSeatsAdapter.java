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

        holder.teacherStudentImg.setImageResource(data.getTeacherStudentImg());
        holder.teacherStudentName.setText(data.getTeacherStudentName());
        holder.teacherStudentId.setText(data.getTeacherStudentId());
        holder.teacherStudentRoom.setText(data.getTeacherStudentDepartment());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, StudentAssignSeatsActivity.class)
                        .putExtra("studentName", data.getTeacherStudentName())
                        .putExtra("studentId", data.getTeacherStudentId())
                        .putExtra("studentDepartment",data.getTeacherStudentDepartment()));

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
