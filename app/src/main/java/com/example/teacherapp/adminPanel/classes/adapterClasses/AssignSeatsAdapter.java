package com.example.teacherapp.adminPanel.classes.adapterClasses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teacherapp.R;
import com.example.teacherapp.adminPanel.classes.modelClasses.TeacherStudentListModelClass;
import com.example.teacherapp.teacherPanel.classes.modelClasses.SelectedItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AssignSeatsAdapter extends RecyclerView.Adapter<AssignSeatsAdapter.AssignSeatsViewHolder> {
    private ArrayList<TeacherStudentListModelClass> mList;
    private Context context;
    ArrayList<TeacherStudentListModelClass> selectedlist = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;

    public AssignSeatsAdapter(ArrayList<TeacherStudentListModelClass> mList, Context context) {
        this.mList = mList;
        this.context = context;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Seating Plan");
    }

    @NonNull
    @Override
    public AssignSeatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_student_list, parent, false);
        return new AssignSeatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignSeatsViewHolder holder, int position) {
        final TeacherStudentListModelClass data = mList.get(position);

        Glide.with(context).load(data.getImageUrl())
                .placeholder(R.drawable.user_pro)
                .into(holder.teacherStudentImg);

        holder.teacherStudentName.setText(data.getUserName());
        holder.teacherStudentId.setText(data.getUserId());
        holder.teacherStudentRoom.setText(data.getStudentDepartment());


        holder.cardView.setCardBackgroundColor(selectedlist.contains(data) ? Color.CYAN : Color.WHITE);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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

    public void Assigned() {
        for (TeacherStudentListModelClass selected : selectedlist) {
            // Get the reference to the specific student's node using UID
            DatabaseReference studentRef = reference.child("Profile Details").child(selected.getUid());

            // Update the seatingStatus to "Assigned"
            studentRef.child("seatingStatus").setValue("Assigned")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Successfully Assigned", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure
                        }
                    });
        }
    }

    class AssignSeatsViewHolder extends RecyclerView.ViewHolder {
        ImageView teacherStudentImg;
        TextView teacherStudentName, teacherStudentId, teacherStudentRoom;
        CardView cardView;

        public AssignSeatsViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherStudentImg = itemView.findViewById(R.id.studentTeacherListImg);
            teacherStudentName = itemView.findViewById(R.id.studentTeacherName);
            teacherStudentId = itemView.findViewById(R.id.studentTeacherId);
            teacherStudentRoom = itemView.findViewById(R.id.studentTeacherRoomNo);
            cardView = itemView.findViewById(R.id.cardviewID);

        }
    }
}
