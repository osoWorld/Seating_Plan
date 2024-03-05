package com.example.teacherapp.modelClass;

public class ViewDutySheetModelClass {
    String userID,seatnumber,username,userdepartment;

    public ViewDutySheetModelClass() {
    }

    public ViewDutySheetModelClass(String userID, String seatnumber, String username, String userdepartment) {
        this.userID = userID;
        this.seatnumber = seatnumber;
        this.username = username;
        this.userdepartment = userdepartment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserdepartment() {
        return userdepartment;
    }

    public void setUserdepartment(String userdepartment) {
        this.userdepartment = userdepartment;
    }
}
