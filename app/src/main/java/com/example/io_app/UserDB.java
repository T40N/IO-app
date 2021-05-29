package com.example.io_app;

public class UserDB {
    private String name;
    private String surname;
    private String email;
    private UserDB.userType userType;
    private String password;
    private boolean status;


    public enum userType{
        standardUser,
        teamLeader,
        administrator,
    }

    public UserDB(String name, String surname, String email, String password, userType userType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.status = false;
    }
    public UserDB(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.status = false;
        this.userType = userType.standardUser;
    }

    public UserDB() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public userType getUserType() {
        return userType;
    }

    public void setUserType(userType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
