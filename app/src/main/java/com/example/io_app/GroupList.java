package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupList extends AppCompatActivity {
    List<GroupDB> groupDBList;
    RecyclerView recyclerView;
    GroupListAdapter groupListAdapter;
    DatabaseReference databaseReference;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list_view);
         recyclerView = findViewById(R.id.groupListRecyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         groupDBList = new ArrayList<>();

         databaseReference = FirebaseDatabase.getInstance().getReference("Group");

         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 for (DataSnapshot ds:snapshot.getChildren()){
                     temp = ds.getValue(String.class);
                    // GroupDB data = ds.getValue(GroupDB.class);
                     GroupDB data = new GroupDB(temp,"Asd");

                     groupDBList.add(data);
                 }
                 GroupListAdapter adapter=new GroupListAdapter(groupDBList);
                 recyclerView.setAdapter(adapter);
             }

             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {

             }
         });
    }
}