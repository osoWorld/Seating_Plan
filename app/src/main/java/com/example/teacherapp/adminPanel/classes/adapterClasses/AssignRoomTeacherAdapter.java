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
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;

import java.util.ArrayList;

public class AssignRoomTeacherAdapter extends RecyclerView.Adapter<AssignRoomTeacherAdapter.AssignRoomTeacherViewHolder>{
    private ArrayList<AssignRoomModelClass> mList;
    private Context context;

    public AssignRoomTeacherAdapter(ArrayList<AssignRoomModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignRoomTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_seat_layout,parent,false);
        return new AssignRoomTeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignRoomTeacherViewHolder holder, int position) {
        final AssignRoomModelClass data = mList.get(position);

        holder.roomIcon.setImageResource(data.getRoomImg());
        holder.roomName.setText(data.getRoomName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AssignRoomTeacherViewHolder extends RecyclerView.ViewHolder{
        ImageView roomIcon;
        TextView roomName;
        public AssignRoomTeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            roomIcon = itemView.findViewById(R.id.roomSeatImage);
            roomName = itemView.findViewById(R.id.roomNumText);

        }
    }
}
