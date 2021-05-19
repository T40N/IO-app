package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.widget.TextView;

public class HomeWindow extends AppCompatActivity {

    private Date currentTime = new Date();
    private String formattedDate;
    private String formattedHour;
    private TextView hourTextV, dateTextV, welcomeTextV;
    private String[] splitDate;

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






       // Log.d("myLOG", currentTime.toString());
    }
}