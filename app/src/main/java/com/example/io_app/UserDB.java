package com.example.io_app;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class UserDB {
    private String name, surname, email, phoneNumber, group, status, userType;

public UserDB(){
    this.name = "name";
    this.surname = "surname";
    this.email = "email";
    this.phoneNumber = "phoneNumber";
    this.group = "group";
    this.status = "status";
    this.userType = "userType";
}
    public UserDB(String name, String surname, String email, String phoneNumber, String group, String status, String userType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.status = status;
        this.userType = userType;
    }


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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public void readName(UserDB user){
        System.out.println(user.name + " " + user.surname);
    }

    @Override
    public int hashCode() {
    int prime = 31;
    char[] name1 = name.toCharArray();
    char[] surname1 = surname.toCharArray();
        int temp = 0 ;
        int temp2 = 0 ;
        int result ;
    for(char ch:name1)
    {
        temp = temp + (int)ch;
    }

        for(char ch:surname1)
        {
            temp2 = temp2 + (int)ch;
        }
        result = temp * prime + temp2;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDB other = (UserDB) obj;
        if (name != other.name)
            return false;
        if (surname != other.surname)
            return false;
        if (email != other.email)
            return false;
        if (status != other.status)
            return false;
        if (userType != other.userType)
            return false;
        if (group != other.group)
            return false;
        if (phoneNumber != other.phoneNumber)
            return false;
        return true;
    }
}
