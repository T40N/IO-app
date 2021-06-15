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
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    FirebaseUser currentUser;


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
                Intent intent = new Intent(AddGroup.this,HomeWindow.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(AddGroup.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(AddGroup.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(AddGroup.this, GroupList.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(AddGroup.this, ChatList.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/"+currentUser.getUid());
                dbRef.child("status").setValue(false);
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(AddGroup.this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}