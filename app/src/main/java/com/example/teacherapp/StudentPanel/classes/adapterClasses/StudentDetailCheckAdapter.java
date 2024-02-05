package com.example.teacherapp.StudentPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.modelClasses.StudentDetailCheckModelClass;

import java.util.ArrayList;

public class StudentDetailCheckAdapter extends RecyclerView.Adapter<StudentDetailCheckAdapter.StudentDetailCheckViewHolder>{
    ArrayList<StudentDetailCheckModelClass> seatList;
    Context context;

    public StudentDetailCheckAdapter(ArrayList<StudentDetailCheckModelClass> seatList, Context context) {
        this.seatList = seatList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentDetailCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seats_layout,parent,false);
        return new StudentDetailCheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailCheckViewHolder holder, int position) {
        //Implement Logic and Arrangement
        final StudentDetailCheckModelClass sList= seatList.get(position);

        holder.rollNumText.setText(sList.getRollNum());
        holder.seatNum.setText(sList.getSeatNumber());
        holder.seatImg.setImageResource(sList.getSeatImg());
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    class StudentDetailCheckViewHolder extends RecyclerView.ViewHolder{
        ImageView seatImg;
        TextView seatNum, rollNumText;
        public StudentDetailCheckViewHolder(@NonNull View itemView) {
            super(itemView);
            seatImg = itemView.findViewById(R.id.seatImage);
            seatNum = itemView.findViewById(R.id.seatNumber);
            rollNumText = itemView.findViewById(R.id.rollNumberSeatText);
            
        }
    }
}
