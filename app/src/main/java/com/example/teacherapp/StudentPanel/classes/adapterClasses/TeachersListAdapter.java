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
import com.example.teacherapp.StudentPanel.classes.modelClasses.TeachersListModelClass;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeachersListAdapter extends RecyclerView.Adapter<TeachersListAdapter.TeachersListViewHolder> {
    ArrayList<TeachersListModelClass> tList;
    Context context;

    public TeachersListAdapter(ArrayList<TeachersListModelClass> tList, Context context) {
        this.tList = tList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeachersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_layout,parent,false);
        return new TeachersListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersListViewHolder holder, int position) {
        TeachersListModelClass list = tList.get(position);

        holder.teacherImg.setImageResource(list.getTeacherImg());
        holder.teacherTxt.setText(list.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    class TeachersListViewHolder extends RecyclerView.ViewHolder{
        ImageView teacherImg;
        TextView teacherTxt;
        public TeachersListViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherImg = itemView.findViewById(R.id.teacherCirImage);
            teacherTxt = itemView.findViewById(R.id.teacherNameList);
        }
    }
}
