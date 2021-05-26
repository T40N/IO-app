package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddGroup extends AppCompatActivity implements View.OnClickListener {



    Button addUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        addUserBtn = findViewById(R.id.addUsersBtn);
        addUserBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
    if(v.getId() == R.id.addUsersBtn){
    //getSupportFragmentManager().beginTransaction().replace(R.id.addUsersContainer, new UserChoiceFragment()).commit();
        Intent intent = new Intent(getApplicationContext(), AddGroupUserList.class);
        startActivity(intent);
         }
    }
}