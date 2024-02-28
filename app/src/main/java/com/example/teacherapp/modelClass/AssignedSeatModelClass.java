package com.example.teacherapp.modelClass;

public class AssignedSeatModelClass {
    String userID,roomnum;

    public AssignedSeatModelClass() {
    }

    public AssignedSeatModelClass(String userID, String roomnum) {
        this.userID = userID;
        this.roomnum = roomnum;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }
}
