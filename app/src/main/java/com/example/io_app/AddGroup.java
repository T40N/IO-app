package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddGroup extends AppCompatActivity implements View.OnClickListener {

    AddGroupUserList adl = new AddGroupUserList();
    ArrayList<UserDB> usersSet = new ArrayList<>();
    UserDB leader = new UserDB();
    EditText groupNameEditText;
    String editTextValue;

    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);

        groupNameEditText = (EditText)findViewById(R.id.groupNameEditText);
        groupNameEditText.setOnClickListener(this);

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
}