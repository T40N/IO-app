package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class HomeWindow extends AppCompatActivity {
    DatabaseReference ref;
    private Date currentTime = new Date();
    private String formattedDate;
    private String formattedHour;
    private TextView hourTextV, dateTextV, welcomeTextV;
    private String[] splitDate;
    private String userName;
    UserDB user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_window_view);

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        hourTextV = findViewById(R.id.hourTextV);
        dateTextV = findViewById(R.id.dateTextV);
        welcomeTextV = findViewById(R.id.welcomeTextV);

        Date currentTime = Calendar.getInstance().getTime();
        formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        formattedHour = DateFormat.getTimeInstance(DateFormat.SHORT).format(currentTime);
        splitDate = formattedDate.split(",");

        dateTextV.setText(splitDate[0] + ", " + splitDate[1]);
        hourTextV.setText(formattedHour);
        welcomeTextV.setText("Witaj z powrotem " + user.getName() + "!" );


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                user = snapshot.getValue(UserDB.class);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }
}