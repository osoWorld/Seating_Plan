package com.example.teacherapp.modelClass;

public class DutyDetailsModeClass {
    String TeacherID,RoomData,StudentID,studentseatnumber;

    public DutyDetailsModeClass() {
    }

    public DutyDetailsModeClass(String teacherID, String roomData, String studentID, String studentseatnumber) {
        TeacherID = teacherID;
        RoomData = roomData;
        StudentID = studentID;
        this.studentseatnumber = studentseatnumber;
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
}
