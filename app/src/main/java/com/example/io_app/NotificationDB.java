package com.example.io_app;

public class NotificationDB {
    private String reciever, taskName, notificationTime;

    public NotificationDB(String reciever, String taskName, String notificationTime) {
        this.reciever = reciever;
        this.taskName = taskName;
        this.notificationTime = notificationTime;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
