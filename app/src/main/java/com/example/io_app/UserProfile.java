package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView userName;
    TextView userSurname;
    TextView userEmail;
    FirebaseUser currentUser;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_view);
        userName = (TextView) findViewById(R.id.userName);
        userSurname = (TextView) findViewById(R.id.userSurname);
        userEmail = (TextView) findViewById(R.id.userEmail);
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

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            String[] fullName = currentUser.getDisplayName().split(" ");
            String email = currentUser.getEmail();

            userName.setText(fullName[0]);
            userSurname.setText(fullName[1]);
            userEmail.setText(email);

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
                Intent intent = new Intent(UserProfile.this,HomeWindow.class);
                startActivity(intent);
                break;
            case R.id.profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.calendar:
                intent = new Intent(UserProfile.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(UserProfile.this, GroupList.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(UserProfile.this, ChatList.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/"+currentUser.getUid());
                dbRef.child("status").setValue(false);
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(UserProfile.this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}