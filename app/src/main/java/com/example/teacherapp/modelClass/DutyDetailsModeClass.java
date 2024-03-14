package com.example.teacherapp.modelClass;

public class DutyDetailsModeClass {
    String TeacherID,RoomData,StudentID,studentseatnumber,papername;

    public DutyDetailsModeClass() {
    }

    public DutyDetailsModeClass(String teacherID, String roomData, String studentID, String studentseatnumber, String papername) {
        TeacherID = teacherID;
        RoomData = roomData;
        StudentID = studentID;
        this.studentseatnumber = studentseatnumber;
        this.papername = papername;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public String getRoomData() {
        return RoomData;
    }

    public void setRoomData(String roomData) {
        RoomData = roomData;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
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
