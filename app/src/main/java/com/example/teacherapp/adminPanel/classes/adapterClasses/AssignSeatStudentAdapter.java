package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;

import java.util.ArrayList;

public class AssignSeatStudentAdapter extends RecyclerView.Adapter<AssignSeatStudentAdapter.AssignSeatStudentViewHolder> {
    private ArrayList<AssignRoomModelClass> mList;
    private Context context;

    public AssignSeatStudentAdapter(ArrayList<AssignRoomModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignSeatStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_seat_layout,parent,false);
        return new AssignSeatStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignSeatStudentViewHolder holder, int position) {
        final AssignRoomModelClass data = mList.get(position);

        holder.roomIcon.setImageResource(data.getRoomImg());
        holder.roomName.setText(data.getRoomName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                holder.roomName.setTextColor(Color.RED);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AssignSeatStudentViewHolder extends RecyclerView.ViewHolder{
        ImageView roomIcon;
        TextView roomName;
        public AssignSeatStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            roomIcon = itemView.findViewById(R.id.assignSeatImg);
            roomName = itemView.findViewById(R.id.assignSeatNumber);

        }
    }
}
