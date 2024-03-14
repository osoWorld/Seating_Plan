package com.example.teacherapp.modelClass;

public class AssignDutySheetModelClass {
    String StudentuserID,userRoom,Teachername,TeacherID,studentseatnumber,papername;

    public AssignDutySheetModelClass(String studentuserID, String userRoom, String teachername, String teacherID, String studentseatnumber, String papername) {
        StudentuserID = studentuserID;
        this.userRoom = userRoom;
        Teachername = teachername;
        TeacherID = teacherID;
        this.studentseatnumber = studentseatnumber;
        this.papername = papername;
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

    public String getStudentuserID() {
        return StudentuserID;
    }

    public void setStudentuserID(String studentuserID) {
        StudentuserID = studentuserID;
    }

    public String getStudentseatnumber() {
        return studentseatnumber;
    }

    public void setStudentseatnumber(String studentseatnumber) {
        this.studentseatnumber = studentseatnumber;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }
}
