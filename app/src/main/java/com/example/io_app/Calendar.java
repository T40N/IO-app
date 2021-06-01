package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

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

                Intent intent = new Intent(Calendar.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.groups:
                intent = new Intent(Calendar.this, AddGroup.class);
                startActivity(intent);
                break;
            case R.id.tasks:
                intent = new Intent(Calendar.this, AddTask.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(Calendar.this, ChatList.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}