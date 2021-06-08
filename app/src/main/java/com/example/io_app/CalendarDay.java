package com.example.io_app;

import android.graphics.RectF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarDay extends Fragment implements MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EmptyViewClickListener{
    private WeekView weekView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_day_view, container, false);
        weekView = (WeekView) view.findViewById(R.id.oneDayView);
        Calendar cal = Calendar.getInstance();

        weekView.setEmptyViewClickListener(this);
        weekView.setMonthChangeListener(this);

        weekView.goToHour(cal.get(Calendar.HOUR_OF_DAY));
        weekView.setVerticalScrollBarEnabled(false);
        weekView.setMinDate(cal);
        weekView.setMaxDate(cal);
        return view;
    }

    @Nullable
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> matchingTasks = new ArrayList<>();
        for(WeekViewEvent task : Login.userTasks){
            if(eventMatches(task, newYear, newMonth)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    @Override
    public void onEmptyViewClicked(@NotNull Calendar calendar) {

    }

    @Override
    public void onEventClick(@NotNull WeekViewEvent weekViewEvent, @NotNull RectF rectF) {

    }

    private static boolean eventMatches(WeekViewEvent event, int year, int month){
        if(event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1){
            return true;
        } else {
            return false;
        }
    }

}