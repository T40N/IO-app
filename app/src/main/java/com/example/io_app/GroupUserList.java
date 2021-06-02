package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.io_app.adapter.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class GroupUserList extends AppCompatActivity{

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    DatabaseReference databaseReference;
    String temp;
    List<UserDB> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_user_list_view);

        recyclerView = findViewById(R.id.groupUserListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        members = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("members");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    temp = ds.getValue(String.class);
                    UserDB data = new UserDB();

                    members.add(data);
                }
                userAdapter =new UserAdapter(members);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}