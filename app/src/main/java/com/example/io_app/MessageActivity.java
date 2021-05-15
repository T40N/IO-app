package com.example.io_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    ImageButton send_button;
    EditText message_input;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        send_button = findViewById(R.id.send_button);
        message_input = findViewById(R.id.message_input);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message_input.getText().toString();

                if (msg.equals("")) {
                    Toast.makeText(MessageActivity.this, "Empty text", Toast.LENGTH_SHORT).show();
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