package com.example.io_app;

public class GroupDB {
    private String groupName, groupLeader;

    public GroupDB(String groupName, String groupLeader) {
        this.groupName = groupName;
        this.groupLeader = groupLeader;
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
}
