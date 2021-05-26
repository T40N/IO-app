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
import java.util.Set;

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.MyViewHolder> {
    int row_index = -1;
    Context context;
    ArrayList<UserDB> list;
    RecyclerViewClickListener listener;
    Set<UserDB> set = new HashSet<UserDB>() ;

    public AddGroupAdapter(Context context, ArrayList<UserDB> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_user_item,parent,false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        UserDB user = list.get(position);
        holder.firstName.setText(user.getName());
        holder.lastName.setText(user.getSurname());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
                set.add(user);
                Iterator<UserDB> iterator = set.iterator();
                UserDB n;
                //As expected, the node with 1, 2 only show up once here when printing out all the nodes in the set
                while(iterator.hasNext()) {
                    n = iterator.next();
                    System.out.println(n.getName() + " " + n.getSurname());
                }
            }
        });

        if (row_index==position) {                          //DO POPRAWY ODZNACZANIE!!!!!!!
            holder.linearLayout.setBackgroundColor(Color.RED);
        }

            /*else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#dddddd"));
        }*/


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
