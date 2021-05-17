package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChatList extends AppCompatActivity {

    ImageButton addButton;

    RecyclerView personalRecyclerView;
    RecyclerView groupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list_view);

        addButton = findViewById(R.id.addChatButton);
        personalRecyclerView = findViewById(R.id.personalMessageRecyclerView);
        groupRecyclerView = findViewById(R.id.groupMessageRecyclerView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: creat new chat
                System.out.println("Adding new chat");
            }
        });
    }
}