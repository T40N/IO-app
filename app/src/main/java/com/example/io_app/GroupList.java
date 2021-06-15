package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
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

public class GroupList extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceGroupName;
    Button newTask;
    Button newGroup;
    FirebaseUser user;
    RecyclerView recyclerView;
    ArrayList<TaskDB> list;
    GroupListAdapter adapter;
    TaskDB task;
    String uID, grpName;
    GroupListAdapter.RecyclerViewClickListener listener;
    TextView groupNameTextV,seeFellowsTextV;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list_view);

        recyclerView = findViewById(R.id.taskRecyclerview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uID).child("Tasks");
        databaseReferenceGroupName = FirebaseDatabase.getInstance().getReference().child("Users").child(uID).child("group");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groupNameTextV = findViewById(R.id.groupNameTextView);
        //seeFellowsTextV = findViewById(R.id.seeFellows);
        //seeFellowsTextV.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.fellows_icon,0);
        //setOnClickListener();

        newGroup = findViewById(R.id.btn_createGroup);
        newGroup.setOnClickListener(this);
        if(!Login.loggedUser.getUserType().equals("administrator")){
            newGroup.setVisibility(View.INVISIBLE);
        }

        newTask = findViewById(R.id.btn_addTask);
        newTask.setOnClickListener(this);


        list = new ArrayList<>();

        adapter = new GroupListAdapter(this, list, listener);
        recyclerView.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    task = dataSnapshot.getValue(TaskDB.class);
                    list.add(task);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        databaseReferenceGroupName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                grpName = snapshot.getValue(String.class);
                groupNameTextV.setText(grpName);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        /*seeFellowsTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GroupUserList.class);
                startActivity(intent);
            }
        });*/

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        if(user != null){
            String[] fullName = user.getDisplayName().split(" ");

            TextView navName = headerView.findViewById(R.id.nameOnMenuID);
            navName.setText(fullName[0] + " " + fullName[1]);
            TextView onlineText = headerView.findViewById(R.id.onlineIndicID);
            onlineText.setText("Online");
        }

    }

    /*private void setOnClickListener() {
        listener = new GroupListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }*/


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_createGroup){
            Intent intent = new Intent(GroupList.this, AddGroup.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btn_addTask){
            Intent intent = new Intent(GroupList.this, AddTask.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_view:
                Intent intent = new Intent(GroupList.this,HomeWindow.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(GroupList.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(GroupList.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.groups:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.chat:
                intent = new Intent(GroupList.this, ChatList.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/"+currentUser.getUid());
                dbRef.child("status").setValue(false);
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(GroupList.this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}