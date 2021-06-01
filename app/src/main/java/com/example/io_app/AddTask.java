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

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.io_app.placeholder.CustomSpinnerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddTask extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    String groupName;
    EditText taskName;
    TextView groupNameLabel;
    Button btnDate;
    Button btnTime;
    Button btnAccept;
    int taskHour, taskMinute, taskDay, taskMonth, taskYear;
    DatabaseReference dbRef;
    Spinner spinner;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_view);
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
        groupName = "crash";    //tu bedzie pobierana wartosc z poprzedniego activity

        retrieveMembers();
        groupNameLabel = findViewById(R.id.tV_groupName);
        groupNameLabel.setText(groupName);
        taskName = findViewById(R.id.txt_title_change);
        spinner = findViewById(R.id.spinner_members);
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this,R.layout.custom_spinner_adapter,retrieveMembers());
        spinner.setAdapter(customSpinnerAdapter);

        btnDate = findViewById(R.id.btn_date);
        btnDate.setOnClickListener(this);
        btnTime = findViewById(R.id.btn_time);
        btnTime.setOnClickListener(this);
        btnAccept = findViewById(R.id.btn_add_task);
        btnAccept.setOnClickListener(this);
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
                Intent intent = new Intent(AddTask.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(AddTask.this,Calendar.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(AddTask.this, AddGroup.class);
                startActivity(intent);
                break;
            case R.id.tasks:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.chat:
                intent = new Intent(AddTask.this, ChatList.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
        

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_date:
                getDate();
                break;
            case R.id.btn_time:
                getTime();
                break;
            case R.id.btn_add_task:
                addTask();
                break;
        }
    }

    private void getTime(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                taskHour = selectedHour;
                taskMinute = selectedMinute;
                btnTime.setText(String.format(Locale.getDefault(),"%02d:%02d",taskHour,taskMinute));
            }
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,style,onTimeSetListener,taskHour,taskMinute,true);
        timePickerDialog.setTitle("Wybierz godzinę");
        timePickerDialog.show();
    }

    private void getDate(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                taskYear = year;
                taskMonth = month + 1;
                taskDay = dayOfMonth;
                btnDate.setText(String.format(Locale.getDefault(),"%02d/%02d/%02d",taskDay,taskMonth,taskYear));
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,style,dateSetListener,Calendar.getInstance().get(Calendar.YEAR),
                                                Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle("Wybierz date");
        datePickerDialog.show();
    }

    private ArrayList<UserDB> retrieveMembers(){
        ArrayList<UserDB> memberArray = new ArrayList<>();
        dbRef = FirebaseDatabase.getInstance().getReference("Groups/" + groupName + "/members");
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                UserDB user = snapshot.getValue(UserDB.class);
                memberArray.add(user);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) { }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(AddTask.this,"Błąd odczytu z bazy danych", Toast.LENGTH_LONG).show();
            }
        });
        return memberArray;
    }

    private void addTask(){
        String taskTile = taskName.getText().toString();
        UserDB member = (UserDB) spinner.getSelectedItem();
        TaskDB newTask = new TaskDB(taskTile,groupName,member.getId(),taskDay,taskMonth,taskYear,taskHour,taskMinute);

        dbRef = FirebaseDatabase.getInstance().getReference("Users/" + member.getId());
        dbRef.child("Tasks").push().setValue(newTask);
        Toast.makeText(this, "Dodano nowe zadanie", Toast.LENGTH_SHORT).show();
    }
}