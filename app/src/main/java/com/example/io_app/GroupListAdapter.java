package com.example.io_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskDB> list;
    RecyclerViewClickListener listener;




    public GroupListAdapter(Context context, ArrayList<TaskDB> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;

    }



    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_list_item,parent,false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        TaskDB task = list.get(position);
        holder.taskName.setText(task.getTaskName());
        if (task.getTaskDay() < 10){
            holder.taskTime.setText("0" + task.getTaskDay() + "." + task.getTaskMonth());
        }

        if (task.getTaskMonth() < 10){
            holder.taskTime.setText(task.getTaskDay() + ".0" + task.getTaskMonth());
        }

        else {
            holder.taskTime.setText(task.getTaskDay() + "." + task.getTaskMonth());
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView taskName, taskTime;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskNameTextView);
            taskTime = itemView.findViewById(R.id.taskDateTextView);
            itemView.setOnClickListener(this);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.TaskLinearLayout);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}