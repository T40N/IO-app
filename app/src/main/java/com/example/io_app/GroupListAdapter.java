package com.example.io_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GroupListAdapter extends RecyclerView.Adapter {
    List<GroupDB> groupDBList;

    public GroupListAdapter(List<GroupDB> groupDBList) {
        this.groupDBList = groupDBList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item_view, parent,false);
        ViewAdapter viewAdapter = new ViewAdapter(view);

       return viewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        ViewAdapter viewAdapter = (ViewAdapter) holder;

        GroupDB groupDB = groupDBList.get(position);
        viewAdapter.groupName.setText(groupDB.getGroupName());

    }

    @Override
    public int getItemCount() {
        return groupDBList.size();
    }

    public class ViewAdapter extends RecyclerView.ViewHolder{

        TextView groupName;
        public ViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.groupName);
        }
    }
}
