package com.example.teacherapp.StudentPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.StudentPanel.classes.modelClasses.StudentDetailCheckModelClass;

import java.util.ArrayList;

public class StudentDetailCheckAdapter extends RecyclerView.Adapter<StudentDetailCheckAdapter.StudentDetailCheckViewHolder>{
    ArrayList<StudentDetailCheckModelClass> seatList;
    Context context;
    String Snumber;

    public StudentDetailCheckAdapter(ArrayList<StudentDetailCheckModelClass> seatList, Context context, String snumber) {
        this.seatList = seatList;
        this.context = context;
        Snumber = snumber;
        System.out.println("Dynamic Room Number:" + Snumber);
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
        // Check if the current room number matches the dynamic room number
        if (sList.getSeatNumber().equals(Snumber.trim())) {
            // Change background color if the room number matches the dynamic room number
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.fav_blue));
        } else {
            // Set default background color
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
        }
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
