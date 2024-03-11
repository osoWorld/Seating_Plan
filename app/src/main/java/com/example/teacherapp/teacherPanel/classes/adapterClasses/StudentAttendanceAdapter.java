package com.example.teacherapp.teacherPanel.classes.adapterClasses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.teacherPanel.classes.modelClasses.StudentAttendanceModelClass;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.StudentAttendanceViewHolder> {
    private ArrayList<StudentAttendanceModelClass> mList;
    ArrayList<StudentAttendanceModelClass> selectedlist = new ArrayList<>();

    private Context context;


    public StudentAttendanceAdapter(ArrayList<StudentAttendanceModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_sheet_layout, parent, false);
        return new StudentAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAttendanceViewHolder holder, int position) {
        final StudentAttendanceModelClass data = mList.get(position);

        Glide.with(context)
                .load(data.getImageUrl())
                .placeholder(R.drawable.user_pro).
                into(holder.studentImage);

        holder.studentName.setText(data.getUserName());
        holder.studentRollNumber.setText(data.getUserId());
        holder.studentSeatNumber.setText(data.getStudentDepartment());

//        data.setStudentAttendanceStatus("Absent");
        holder.cardView.setCardBackgroundColor(selectedlist.contains(data) ? Color.CYAN : Color.WHITE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (selectedlist.contains(data)) {
                   selectedlist.remove(data);
                   holder.cardView.setCardBackgroundColor(Color.WHITE);

               } else {
                   selectedlist.add(data);
                   holder.cardView.setCardBackgroundColor(Color.CYAN);
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public ArrayList<StudentAttendanceModelClass> getSelected(){
        return selectedlist;
    }

    class StudentAttendanceViewHolder extends RecyclerView.ViewHolder {
        CircleImageView studentImage;
        TextView studentName, studentRollNumber, studentSeatNumber;
        CardView  cardView;

        public StudentAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            studentImage = itemView.findViewById(R.id.studentAttendanceImg);
            studentName = itemView.findViewById(R.id.studentAttendanceName);
            studentRollNumber = itemView.findViewById(R.id.studentAttendanceRollNo);
            studentSeatNumber = itemView.findViewById(R.id.studentAttendanceSeatNo);
            cardView = itemView.findViewById(R.id.cardviewatterndanceId);

        }
    }
}
