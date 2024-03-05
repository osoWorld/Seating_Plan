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
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;

import java.util.ArrayList;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.StudentViewHolder>{
    private ArrayList<StudentAttendanceModelClass> mList;
    private Context context;

    public StudentAttendanceAdapter(ArrayList<StudentAttendanceModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_list, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final StudentAttendanceModelClass data = mList.get(position);

        Glide.with(context).load(data.getImageUrl())
                .placeholder(R.drawable.user_pro)
                .into(holder.teacherStudentImg);

        holder.teacherStudentName.setText(data.getUserName());
        holder.teacherStudentId.setText(data.getUserId());
        holder.teacherStudentRoom.setText(data.getStudentDepartment());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        ImageView teacherStudentImg;
        TextView teacherStudentName, teacherStudentId, teacherStudentRoom;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherStudentImg = itemView.findViewById(R.id.studentTeacherListImg);
            teacherStudentName = itemView.findViewById(R.id.studentTeacherName);
            teacherStudentId = itemView.findViewById(R.id.studentTeacherId);
            teacherStudentRoom = itemView.findViewById(R.id.studentTeacherRoomNo);

        }
    }
}