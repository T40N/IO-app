package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddGroup extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    AddGroupUserList adl = new AddGroupUserList();
    ArrayList<UserDB> usersSet = new ArrayList<>();
    UserDB leader = new UserDB();
    EditText groupNameEditText;
    String editTextValue;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);

        groupNameEditText = (EditText)findViewById(R.id.groupNameEditText);
        groupNameEditText.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.addUsersContainer);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.groupNameEditText)
        {
            groupNameEditText.getText().clear();
        }

    if(v.getId() == R.id.nextBtn){
        editTextValue = groupNameEditText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), AddGroupUserList.class);
        intent.putExtra("GROUP_NAME",editTextValue);
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
                Intent intent = new Intent(AddGroup.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(AddGroup.this,Calendar.class);
                startActivity(intent);
                break;
            case R.id.groups:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.tasks:
                intent = new Intent(AddGroup.this, AddTask.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(AddGroup.this, ChatList.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}