package com.example.teacherapp.modelClass;

public class AssignDutySheetModelClass {
    String StudentuserID,userRoom,Teachername,TeacherID,studentseatnumber;

    public AssignDutySheetModelClass(String studentuserID, String userRoom, String teachername, String teacherID, String studentseatnumber) {
        StudentuserID = studentuserID;
        this.userRoom = userRoom;
        Teachername = teachername;
        TeacherID = teacherID;
        this.studentseatnumber = studentseatnumber;
    }

    public AssignDutySheetModelClass() {
    }

    public String getUserID() {
        return StudentuserID;
    }

    public void setUserID(String userID) {
        this.StudentuserID = userID;
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

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }
}
