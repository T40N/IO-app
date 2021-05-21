package com.example.io_app;

public class MessageDB {
    private String message, userSend, userRecieve;

    public MessageDB(String message, String userSend, String userRecieve) {
        this.message = message;
        this.userSend = userSend;
        this.userRecieve = userRecieve;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getUserRecieve() {
        return userRecieve;
    }

    public void setUserRecieve(String userRecieve) {
        this.userRecieve = userRecieve;
    }
}
