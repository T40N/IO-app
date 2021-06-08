package com.example.io_app;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.io_app.adapter.UserAdapter;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<UserDB> usersList;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    FirebaseUser currentUser;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list_view);

        recyclerView = findViewById(R.id.personalMessageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersList = new ArrayList<>();

        readUsers();
    }

    private void readUsers() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                usersList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    UserDB user = ds.getValue(UserDB.class);

                    usersList.add(user);
                }

                userAdapter = new UserAdapter(usersList);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        View headerView = navigationView.getHeaderView(0);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            String[] fullName = currentUser.getDisplayName().split(" ");

            TextView navName = headerView.findViewById(R.id.nameOnMenuID);
            navName.setText(fullName[0] + " " + fullName[1]);
            TextView onlineText = headerView.findViewById(R.id.onlineIndicID);
            onlineText.setText("Online");
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
                Intent intent = new Intent(ChatList.this,HomeWindow.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(ChatList.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(ChatList.this, Calendar.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(ChatList.this, GroupList.class);
                startActivity(intent);
                break;
            case R.id.tasks:
                intent = new Intent(ChatList.this, AddTask.class);
                startActivity(intent);
                break;
            case R.id.chat:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(ChatList.this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}