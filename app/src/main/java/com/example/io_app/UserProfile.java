package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {
    TextView userName;
    TextView userSurname;
    TextView userEmail;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_view);
        userName = (TextView) findViewById(R.id.userName);
        userSurname = (TextView) findViewById(R.id.userSurname);
        userEmail = (TextView) findViewById(R.id.userEmail);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            String[] fullName = currentUser.getDisplayName().split(" ");
            String email = currentUser.getEmail();

            userName.setText(fullName[0]);
            userSurname.setText(fullName[1]);
            userEmail.setText(email);
        }

    }
}