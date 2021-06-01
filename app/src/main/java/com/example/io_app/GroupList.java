package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupList extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button seeCoworkers;
    FirebaseUser user;
    RecyclerView recyclerView;
    ArrayList<TaskDB> list;
    GroupListAdapter adapter;
    TaskDB task;
    String uID;
    GroupListAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list_view);

        recyclerView = findViewById(R.id.taskRecyclerview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setOnClickListener();

        list = new ArrayList<>();

        adapter = new GroupListAdapter(this, list, listener);
        recyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    task = dataSnapshot.getValue(TaskDB.class);

                    if (uID == task.getUserReceiver())
                        list.add(task);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

      private void setOnClickListener() {
        listener = new GroupListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }


}