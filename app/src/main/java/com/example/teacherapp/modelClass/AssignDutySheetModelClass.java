package com.example.teacherapp.modelClass;

public class AssignDutySheetModelClass {
    String userID,userRoom,Teachername;

    public AssignDutySheetModelClass(String userID, String userRoom, String teachername) {
        this.userID = userID;
        this.userRoom = userRoom;
        Teachername = teachername;
    }

    public AssignDutySheetModelClass() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }

    public String getTeachername() {
        return Teachername;
    }

    public void setTeachername(String teachername) {
        Teachername = teachername;
    }
}
