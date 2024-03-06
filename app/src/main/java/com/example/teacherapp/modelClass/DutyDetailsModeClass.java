package com.example.teacherapp.modelClass;

public class DutyDetailsModeClass {
    String TeacherID,RoomData,StudentID;

    public DutyDetailsModeClass() {
    }

    public DutyDetailsModeClass(String teacherID, String roomData, String studentID) {
        TeacherID = teacherID;
        RoomData = roomData;
        StudentID = studentID;
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
}
