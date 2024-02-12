package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;

import java.util.ArrayList;

public class TeacherStudentListAdapter extends RecyclerView.Adapter<TeacherStudentListAdapter.TeacherStudentListViewHolder>{
    private ArrayList<TeacherStudentListModelClass> mList;
    private Context context;

    public TeacherStudentListAdapter(ArrayList<TeacherStudentListModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherStudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_list,parent,false);
        return new TeacherStudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherStudentListViewHolder holder, int position) {
        final TeacherStudentListModelClass data = mList.get(position);

        holder.teacherStudentImg.setImageResource(data.getTeacherStudentImg());
        holder.teacherStudentName.setText(data.getTeacherStudentName());
        holder.teacherStudentId.setText(data.getTeacherStudentId());
        holder.teacherStudentRoom.setText(data.getTeacherStudentRoom());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TeacherStudentListViewHolder extends RecyclerView.ViewHolder{
        ImageView teacherStudentImg;
        TextView teacherStudentName, teacherStudentId, teacherStudentRoom;
        public TeacherStudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherStudentImg = itemView.findViewById(R.id.studentTeacherListImg);
            teacherStudentName = itemView.findViewById(R.id.studentTeacherName);
            teacherStudentId = itemView.findViewById(R.id.studentTeacherId);
            teacherStudentRoom = itemView.findViewById(R.id.studentTeacherRoomNo);
        }
    }
}
