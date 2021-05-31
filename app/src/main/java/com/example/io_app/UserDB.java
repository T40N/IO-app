package com.example.io_app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class UserDB implements Parcelable {
    private String id,name, surname, email, group, userType;
    private boolean status;

    public UserDB() {
        this.id = "id";
        this.name = "name";
        this.surname = "surname";
        this.email = "email";

        this.group = "group";
        this.status = false;
        this.userType = "userType";
    }

    public UserDB(String id, String name, String surname, String email, String group, boolean status, String userType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void readName(UserDB user) {
        System.out.println(user.name + " " + user.surname);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        char[] name1 = name.toCharArray();
        char[] surname1 = surname.toCharArray();
        int temp = 0;
        int temp2 = 0;
        int result;
        for (char ch : name1) {
            temp = temp + (int) ch;
        }

        for (char ch : surname1) {
            temp2 = temp2 + (int) ch;
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

        return true;
    }

    public UserDB(Parcel in) {
        String s1 = String.valueOf(this.status);
        String[] data = new String[7];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.id = data[0];
        this.name = data[1];
        this.surname = data[2];
        this.email = data[3];
        this.group = data[4];
        s1 = data[5];
        this.userType = data[6];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String s1 = String.valueOf(this.status);
        dest.writeStringArray(new String[]{
                this.id,
                this.name,
                this.surname,
                this.email,
                this.group,
                s1,
                this.userType,
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserDB createFromParcel(Parcel in) {
            return new UserDB(in);
        }

        public UserDB[] newArray(int size) {
            return new UserDB[size];
        }
    };
}