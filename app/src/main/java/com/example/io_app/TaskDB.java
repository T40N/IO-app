package com.example.io_app;


import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;


public class TaskDB {
    private String taskName;
    private String group;
    private String userReceiver;
    private Calendar dateTime;

    public TaskDB(String taskName, String group, String userReceiver, Calendar dateTime) {
        this.taskName = taskName;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(String userReceiver) {
        this.userReceiver = userReceiver;
    }


}
