package com.example.io_app;

import java.util.ArrayList;

public class GroupDB {
    private String groupName, groupLeader;
    private ArrayList<UserDB> members;

    public GroupDB(){
        this.groupName = "groupName";
        this.groupLeader = "groupLeader";
    }

    public GroupDB(String groupName, String groupLeader, ArrayList<UserDB> members) {
        this.groupName = groupName;
        this.groupLeader = groupLeader;
        this.members = members;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public ArrayList<UserDB> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<UserDB> members) {
        this.members = members;
    }
}
