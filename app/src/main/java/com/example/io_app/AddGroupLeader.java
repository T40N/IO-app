package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddGroupLeader extends AppCompatActivity {

    RecyclerView recyclerView ;
    AddLeaderAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference ref;
    ArrayList<UserDB> list;
    Button createBtn;
    ArrayList<UserDB> usersSet;
    ArrayList<String> usersId;
    String groupNm;


    AddLeaderAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_leader);

        recyclerView = findViewById(R.id.userListRecycleView2);
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        setOnClickListener();

        list = new ArrayList<>();
        usersSet = new ArrayList<>();
        usersId = new ArrayList<>();



        adapter = new AddLeaderAdapter(this,list, listener);
        recyclerView.setAdapter(adapter);


        createBtn = findViewById(R.id.createGroupBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = FirebaseDatabase.getInstance().getReference().child("Groups");
                Bundle data = getIntent().getExtras();
                UserDB leader = adapter.getUser();
                usersSet = data.getParcelableArrayList("ADDED_USERS_LIST");
                groupNm = data.getString("GROUP_NAME");
                GroupDB group = new GroupDB(groupNm, leader.getId(),usersSet);

                ref.child(groupNm).setValue(group);

            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    UserDB user = dataSnapshot.getValue(UserDB.class);
                    list.add(user);


                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void setOnClickListener() {
        listener = new AddLeaderAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }
}