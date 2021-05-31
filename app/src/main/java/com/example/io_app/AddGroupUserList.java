package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddGroupUserList extends AppCompatActivity {

    RecyclerView recyclerView ;
    AddGroupAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference ref;
    ArrayList<UserDB> list;
    ArrayList<String> usersId;
    Button next2Btn;
    String groupName;

    AddGroupAdapter.RecyclerViewClickListener listener;
    UserDB mem1,mem2,mem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_user_list);

        recyclerView = findViewById(R.id.userListRecycleView);
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // mem1 = new UserDB("Adam", "Saadam","1kasdrew@oppl","",true,"administrator");
      //  mem2 = new UserDB("Piotr", "Kostka","a3aafd3@oppl","",true,"administrator");
       // mem3 = new UserDB("Bartosz", "Góra","12231ad32gg4@oppl","",true,"administrator");

      //  ref.child(mem2.getEmail()).setValue(mem2);
        //ref.child(mem3.getEmail()).setValue(mem3);


        setOnClickListener();

        list = new ArrayList<>();



        adapter = new AddGroupAdapter(this,list, listener);
        recyclerView.setAdapter(adapter);


        next2Btn = findViewById(R.id.next2Btn);
        next2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle data = getIntent().getExtras();

                groupName = data.getString("GROUP_NAME");

                Intent intent = new Intent(getApplicationContext(),AddGroupLeader.class);
                ArrayList<String> addedUsersList = new ArrayList<String>(adapter.getSet());

                intent.putExtra("ADDED_USERS_LIST", addedUsersList);
                intent.putExtra("GROUP_NAME", groupName);
                startActivity(intent);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    UserDB user = dataSnapshot.getValue(UserDB.class);
                    list.add(user);

                }
                 adapter.notifyDataSetChanged();
                //String s = ref.push().getKey();
               // System.out.println(s);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void setOnClickListener() {
        listener = new AddGroupAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }


}