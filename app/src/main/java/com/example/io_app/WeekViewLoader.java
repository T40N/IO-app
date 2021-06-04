package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeekViewLoader {

    public static List<? extends  WeekViewEvent> loadEvents(int newYear, int newMonth){
        List<WeekViewEvent> tasks = new ArrayList<>();
        List<WeekViewEvent> matchingTasks = new ArrayList<>();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/"+currentUser.getUid()+"/Tasks");
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                TaskDB task = snapshot.getValue(TaskDB.class);
                tasks.add(task.toWeekViewEvent());
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) { }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });

        for(WeekViewEvent task : tasks){
            if(eventMatches(task,newYear,newMonth)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private static boolean eventMatches(WeekViewEvent event, int year, int month){
        return event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month;
    }
}
