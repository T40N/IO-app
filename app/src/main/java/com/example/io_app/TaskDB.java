package com.example.io_app;


import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;


public class TaskDB {
    private String taskName;
    private String userCreator;
    private String userReceiver;
    private Calendar dateTime;

    public TaskDB(String taskName, String userCreator, String userReceiver, Calendar dateTime) {
        this.taskName = taskName;
        this.userCreator = userCreator;
        this.userReceiver = userReceiver;
        this.dateTime = dateTime;
    }

    public WeekViewEvent toWeekViewEvent(int i){
        Calendar startTime = (Calendar) this.dateTime.clone();
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY,startTime.get(Calendar.HOUR_OF_DAY) + 1);
        return new WeekViewEvent(String.valueOf(i), this.taskName,startTime,endTime);
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(String userReceiver) {
        this.userReceiver = userReceiver;
    }


}
