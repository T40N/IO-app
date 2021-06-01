package com.example.io_app.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.io_app.Chat;
import com.example.io_app.R;
import com.example.io_app.UserDB;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserDB> mUsers;

    public UserAdapter(List<UserDB> mUsers) {
        this.mUsers = mUsers;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_view, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        UserDB user = mUsers.get(position);
        holder.userName.setText(user.getName() + " " + user.getSurname());
        holder.profileImage.setImageResource(R.drawable.ic_profile_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Chat.class);
                intent.putExtra("userId", user.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView profileImage;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
