package com.example.io_app;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.io_app.adapter.UserAdapter;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<UserDB> mUsers;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    private List<String> usersList;

    FirebaseUser fuser;
    DatabaseReference reference;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageButton newChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list_view);

        newChat = findViewById(R.id.addChatButton);

        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UsersList.class);
                view.getContext().startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.personalMessageRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
                usersList.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    MessageDB message = snapshot.getValue(MessageDB.class);

                    if (message.getSender().equals(fuser.getUid()))
                        usersList.add(message.getReceiver());

                    if (message.getReceiver().equals(fuser.getUid()))
                        usersList.add(message.getSender());
                }
                
                readChats();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void readChats() {
        mUsers = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {
                mUsers.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    UserDB user = snapshot.getValue(UserDB.class);

                    for (String id : usersList) {
                        if (user.getId().equals(id)) {
                            if (mUsers.size() != 0) {
                                for (UserDB user1 : mUsers) {
                                    if (!user.getId().equals(user1.getId()))
                                        mUsers.add(user);
                                }
                            } else {
                                mUsers.add(user);
                            }
                        }
                    }
                }

                userAdapter = new UserAdapter(mUsers);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_view:
                Intent intent = new Intent(ChatList.this,UserProfile.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(ChatList.this,Calendar.class);
                startActivity(intent);
                break;
            case R.id.groups:
                intent = new Intent(ChatList.this, AddGroup.class);
                startActivity(intent);
                break;
            case R.id.tasks:
                intent = new Intent(ChatList.this, AddTask.class);
                startActivity(intent);
                break;
            case R.id.chat:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return false;
    }
}