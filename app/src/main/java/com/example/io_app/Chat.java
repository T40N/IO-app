
//public class Chat extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.chat_view);
//

//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                UserDB user = snapshot.getValue(UserDB.class);
//                userName.setText(user.getName());
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//    }
//}

package com.example.io_app;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference reference;

    TextView userName;

    Intent intent;

    ImageButton sendMessage;
    EditText message_input;

    RecyclerView chatRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_view);

        intent = getIntent();

        userName = findViewById(R.id.chatName);

        String userEmail = intent.getStringExtra("useremail");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child("1223132gg4@oppl");

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