package com.example.teacherapp.teacherPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.StudentAttendanceViewHolder>{
    private ArrayList<StudentAttendanceModelClass> mList;
    private Context context;

    public StudentAttendanceAdapter(ArrayList<StudentAttendanceModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_sheet_layout,parent,false);
        return new StudentAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAttendanceViewHolder holder, int position) {
        final StudentAttendanceModelClass data = mList.get(position);
        holder.studentImage.setImageResource(data.getStudentImg());
        holder.studentName.setText(data.getStudentName());
        holder.studentRollNumber.setText(data.getStudentRollNumber());
        holder.studentSeatNumber.setText(data.getStudentSeatNumber());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class StudentAttendanceViewHolder extends RecyclerView.ViewHolder {
        CircleImageView studentImage;
        TextView studentName, studentRollNumber, studentSeatNumber;
        CheckBox attendanceCheck;

        public StudentAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            studentImage = itemView.findViewById(R.id.studentAttendanceImg);
            studentName = itemView.findViewById(R.id.studentAttendanceName);
            studentRollNumber = itemView.findViewById(R.id.studentAttendanceRollNo);
            studentSeatNumber = itemView.findViewById(R.id.studentAttendanceSeatNo);
            attendanceCheck = itemView.findViewById(R.id.studentAttendanceCheckBox);

        }
    }
}
