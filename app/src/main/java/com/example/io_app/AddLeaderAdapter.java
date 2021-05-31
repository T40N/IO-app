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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AddLeaderAdapter extends RecyclerView.Adapter<AddLeaderAdapter.MyViewHolder>{
    int row_index = -1;
    UserDB userChosen;
    Context context;
    ArrayList<UserDB> list;
    ArrayList<Integer> usersId;
    String userId;
    AddLeaderAdapter.RecyclerViewClickListener listener;




    public AddLeaderAdapter(Context context, ArrayList<UserDB> list, AddLeaderAdapter.RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        list = new ArrayList<>();
        usersId = new ArrayList<>();
    }



    public UserDB getUser() {
        return userChosen;
    }

    public String getUserId() {
        return userId;
    }

    @NonNull
    @NotNull
    @Override
    public AddLeaderAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_user_item,parent,false);
        return new AddLeaderAdapter.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull AddLeaderAdapter.MyViewHolder holder, int position) {

        UserDB user = list.get(position);
        holder.firstName.setText(user.getName());
        holder.lastName.setText(user.getSurname());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                row_index=position;
                notifyDataSetChanged();
                userChosen = user;

            }
        });

        if (row_index==position) {                          //DO POPRAWY ODZNACZANIE!!!!!!!
            holder.linearLayout.setBackgroundColor(Color.RED);
        }

            else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#dddddd"));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView firstName, lastName;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstNameTv);
            lastName = itemView.findViewById(R.id.lastNameTv);
            itemView.setOnClickListener(this);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
