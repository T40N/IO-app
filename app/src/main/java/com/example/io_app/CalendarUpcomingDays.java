package com.example.io_app;

import android.graphics.RectF;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarUpcomingDays extends Fragment implements MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EmptyViewClickListener {
    WeekView weekView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_upcoming_days_view, container, false);
        weekView = (WeekView) view.findViewById(R.id.threeDaysView);
        Calendar today = Calendar.getInstance();
        Calendar lastDay = (Calendar) today.clone();
        lastDay.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) + 2);

        weekView.setEmptyViewClickListener(this);
        weekView.setMonthChangeListener(this);

        weekView.goToHour(today.get(Calendar.HOUR_OF_DAY));
        weekView.setMinDate(today);
        weekView.setMaxDate(lastDay);
        return view;
    }

    @Nullable
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List <WeekViewEvent> events = (List<WeekViewEvent>) WeekViewLoader.loadEvents(newYear,newMonth);
        return events;
    }

    @Override
    public void onEmptyViewClicked(@NotNull Calendar calendar) {
        Toast.makeText(getActivity(), "Brak wydarzeń w tym czasie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventClick(@NotNull WeekViewEvent weekViewEvent, @NotNull RectF rectF) {
        Toast.makeText(getActivity(), weekViewEvent.getName(), Toast.LENGTH_SHORT).show();
    }
}