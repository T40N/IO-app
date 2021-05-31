package com.example.io_app;

public class UserDB {
    private String name;
    private String surname;
    private String email;
    private String id;
    private String group;
    private UserDB.userType userType;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public enum userType{
        standardUser,
        teamLeader,
        administrator,
    }

    public UserDB(String name, String surname, String email, userType userType) {
        this.name = name;
        this.surname = surname;
        this.email = email;

        this.userType = userType;
        this.status = false;
    }
    public UserDB(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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


}
