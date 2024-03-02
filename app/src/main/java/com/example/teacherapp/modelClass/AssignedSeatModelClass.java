package com.example.teacherapp.modelClass;

public class AssignedSeatModelClass {
    String userID,roomnum,seatnumber;

    public AssignedSeatModelClass() {
    }

    public AssignedSeatModelClass(String userID, String roomnum, String seatnumber) {
        this.userID = userID;
        this.roomnum = roomnum;
        this.seatnumber = seatnumber;
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

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }
}
