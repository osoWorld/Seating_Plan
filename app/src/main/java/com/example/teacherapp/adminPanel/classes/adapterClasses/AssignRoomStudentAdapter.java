package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.adminFragment.AssignSeatsFragment;
import com.example.teacherapp.adminPanel.classes.modelClasses.AssignRoomModelClass;

import java.util.ArrayList;

public class AssignRoomStudentAdapter extends RecyclerView.Adapter<AssignRoomStudentAdapter.AssignRoomStudentViewHolder>{
    private ArrayList<AssignRoomModelClass> mList;
    private Context context;

    public AssignRoomStudentAdapter(ArrayList<AssignRoomModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignRoomStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_seat_layout,parent,false);
        return new AssignRoomStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignRoomStudentViewHolder holder, int position) {
        final AssignRoomModelClass data = mList.get(position);

        holder.roomIcon.setImageResource(data.getRoomImg());
        holder.roomName.setText(data.getRoomName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                (((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frameAssignRoomsCont, new AssignSeatsFragment())).commit();
                Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AssignRoomStudentViewHolder extends RecyclerView.ViewHolder{
        ImageView roomIcon;
        TextView roomName;
        public AssignRoomStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            roomIcon = itemView.findViewById(R.id.roomSeatImage);
            roomName = itemView.findViewById(R.id.roomNumText);

        }
    }
}
