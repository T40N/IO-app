package com.example.io_app;


import java.util.Calendar;


public class TaskDB {
    private String taskName;
    private String userCreator;
    private String userReviever;
    private Calendar dateTime;

    public TaskDB(String taskName, String userCreator, String userReviever, Calendar dateTime) {
        this.taskName = taskName;
        this.userCreator = userCreator;
        this.userReviever = userReviever;
        this.dateTime = dateTime;
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

    public String getUserReviever() {
        return userReviever;
    }

    public void setUserReviever(String userReviever) {
        this.userReviever = userReviever;
    }


}
