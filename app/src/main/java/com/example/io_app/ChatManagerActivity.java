package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChatManagerActivity extends AppCompatActivity {

    ImageButton addButton;

    RecyclerView personalRecyclerView;
    RecyclerView groupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_manager);

        addButton = findViewById(R.id.add_button);
        personalRecyclerView = findViewById(R.id.recyclerView_personal_message);
        groupRecyclerView = findViewById(R.id.recyclerView_group_message);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: creat new chat
                System.out.println("Adding new chat");
            }
        });
    }
}