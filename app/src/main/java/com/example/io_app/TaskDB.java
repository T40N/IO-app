package com.example.io_app;


import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class TaskDB {
    private String taskName;
    private String group;
    private String userReceiver;
    private int taskHour, taskMinute, taskDay, taskMonth, taskYear;
    //private Calendar dateTime;

    public TaskDB(String taskName, String group, String userReceiver, int taskDay, int taskMonth, int taskYear, int taskHour, int taskMinute) {
        this.taskName = taskName;
        this.group = group;
        this.userReceiver = userReceiver;
        this.taskDay = taskDay;
        this.taskMonth = taskMonth;
        this.taskYear = taskYear;
        this.taskHour = taskHour;
        this.taskMinute = taskMinute;
    }

    public TaskDB () {}

    public WeekViewEvent toWeekViewEvent(){
        //Calendar taskDate = new GregorianCalendar(taskYear,taskMonth,taskDay,taskHour,taskMinute);
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, taskHour);
        startTime.set(Calendar.MINUTE, taskMinute);
        startTime.set(Calendar.MONTH, taskMonth-1);
        startTime.set(Calendar.YEAR, taskYear);
        startTime.set(Calendar.DAY_OF_MONTH, taskDay);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.MONTH, taskMonth-1);
        endTime.set(Calendar.HOUR_OF_DAY,startTime.get(Calendar.HOUR_OF_DAY) + 1);
        return new WeekViewEvent(this.taskName, this.taskName,startTime,endTime);
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


    public int getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(int taskHour) {
        this.taskHour = taskHour;
    }

    public int getTaskMinute() {
        return taskMinute;
    }

    public void setTaskMinute(int taskMinute) {
        this.taskMinute = taskMinute;
    }

    public int getTaskDay() {
        return taskDay;
    }

    public void setTaskDay(int taskDay) {
        this.taskDay = taskDay;
    }

    public int getTaskMonth() {
        return taskMonth;
    }

    public void setTaskMonth(int taskMonth) {
        this.taskMonth = taskMonth;
    }

    public int getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(int taskYear) {
        this.taskYear = taskYear;
    }
}
