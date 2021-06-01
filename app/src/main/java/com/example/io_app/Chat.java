package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.io_app.adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat extends AppCompatActivity {

    FirebaseUser fuser;
    DatabaseReference reference;

    TextView userName;

    Intent intent;

    ImageButton sendMessage;
    EditText message_input;

    MessageAdapter messageAdapter;
    List<MessageDB> mMessage;

    RecyclerView chatRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_view);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        chatRecyclerView.setLayoutManager(linearLayoutManager);

        intent = getIntent();

        userName = findViewById(R.id.chatName);

        String userId = intent.getStringExtra("userId");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                UserDB user = snapshot.getValue(UserDB.class);
                userName.setText(user.getName() + " " + user.getSurname());

                readMessage(fuser.getUid(), userId);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });

        sendMessage = findViewById(R.id.sendMessage);
        message_input = findViewById(R.id.message_input);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message_input.getText().toString();

                if (msg.equals("")) {
                    Toast.makeText(Chat.this, "Empty text", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendMessage(fuser.getUid(), userId, msg);
                message_input.setText("");
                Toast.makeText(Chat.this, fuser.getUid(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendMessage(String senderUser, String receiverUser, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", senderUser);
        hashMap.put("receiver", receiverUser);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);
    }

    private void readMessage(String myId, String userId) {
        mMessage = new ArrayList<>();


        reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
                mMessage.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    MessageDB messageDB = snapshot.getValue(MessageDB.class);
                    if (messageDB.getUserRecieve().equals(myId) && messageDB.getUserSend().equals(userId)
                    || messageDB.getUserRecieve().equals(userId) && messageDB.getUserSend().equals(myId))
                        mMessage.add(messageDB);

                    messageAdapter = new MessageAdapter(Chat.this, mMessage);
                    chatRecyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}