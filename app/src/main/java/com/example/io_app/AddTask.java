package com.example.io_app;

import androidx.annotation.NonNull;
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

public class AddTask extends AppCompatActivity implements View.OnClickListener {
    String groupName;
    EditText taskName;
    Button btnDate;
    Button btnTime;
    Button btnAccept;
    int taskHour, taskMinute, taskDay, taskMonth, taskYear;
    DatabaseReference dbRef;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupName = "Dodaj grupe";
        setContentView(R.layout.add_task_view);
        retrieveMembers();
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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_date:
                getDate();
                break;
            case R.id.btn_time:
                getTime();
                break;
            case R.id.btn_add_task:
                String taskTile = taskName.getText().toString();
                UserDB member = (UserDB) spinner.getSelectedItem();
                Calendar taskDate = new GregorianCalendar(taskYear,taskMonth,taskDay,taskHour,taskMinute);
                TaskDB newTask = new TaskDB(taskTile,groupName,member.getName(),taskDate);
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
        dbRef = FirebaseDatabase.getInstance().getReference("Groups/Dodaj grupe/members");
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
}