package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AddGroupUserList extends AppCompatActivity {

    RecyclerView recyclerView ;
    AddGroupAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference ref;
    ArrayList<UserDB> list;

    AddGroupAdapter.RecyclerViewClickListener listener;
    UserDB mem1,mem2,mem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_user_list);

        recyclerView = findViewById(R.id.userListRecycleView);
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* mem1 = new UserDB("asd","asd","asd","asd","asd","asd","asd");
        mem3 = new UserDB("asd2","asd","asd","asd","asd","asd","asd");
        mem2 = new UserDB("asd3","asd","asd","asd","asd","asd","asd");*/

      /*  ref.push().setValue(mem1);
        ref.push().setValue(mem2);
        ref.push().setValue(mem3);*/
        setOnClickListener();
        list = new ArrayList<>();



        adapter = new AddGroupAdapter(this,list, listener);
        recyclerView.setAdapter(adapter);

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
        listener = new AddGroupAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }


}