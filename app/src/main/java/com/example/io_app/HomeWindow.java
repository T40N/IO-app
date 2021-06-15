package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class HomeWindow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Date currentTime = new Date();
    private String formattedDate;
    private String formattedHour;
    private TextView hourTextV, dateTextV, welcomeTextV;
    private String[] splitDate;
    FirebaseUser currentUser;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_window_view);

        hourTextV = findViewById(R.id.hourTextV);
        dateTextV = findViewById(R.id.dateTextV);
        welcomeTextV = findViewById(R.id.welcomeTextV);

       Date currentTime = Calendar.getInstance().getTime();
        formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        formattedHour = DateFormat.getTimeInstance(DateFormat.SHORT).format(currentTime);
        splitDate = formattedDate.split(",");

        dateTextV.setText(splitDate[0] + ", " + splitDate[1]);
        hourTextV.setText(formattedHour);
        welcomeTextV.setText("Witaj z powrotem! " );

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

            TextView navName = headerView.findViewById(R.id.nameOnMenuID);
            navName.setText(fullName[0] + " " + fullName[1]);
            TextView onlineText = headerView.findViewById(R.id.onlineIndicID);
            onlineText.setText("Online");
        }

       // Log.d("myLOG", currentTime.toString());
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
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.profile:
                Intent intent = new Intent(HomeWindow.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(HomeWindow.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(HomeWindow.this, GroupList.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(HomeWindow.this, ChatList.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/"+currentUser.getUid());
                dbRef.child("status").setValue(false);
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(HomeWindow.this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}