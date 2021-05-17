package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Chat extends AppCompatActivity {

    ImageButton sendMessage;
    EditText message_input;

    RecyclerView chatRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_view);


        sendMessage = findViewById(R.id.sendMessage);
        message_input = findViewById(R.id.message_input);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        chatRecyclerView.setLayoutManager(linearLayoutManager);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message_input.getText().toString();

                if (msg.equals("")) {
                    Toast.makeText(Chat.this, "Empty text", Toast.LENGTH_SHORT).show();
                    return;
                }

                //sendMessage();
                message_input.setText("");
            }
        });
    }

    private void sendMessage(String senderUser, String receiverUser, String message) {

    }

}