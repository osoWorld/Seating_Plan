package com.example.teacherapp.modelClass;

public class DutyDetailsModeClass {
    String TeacherID,RoomData;

    public DutyDetailsModeClass() {
    }

    public DutyDetailsModeClass(String teacherID, String roomData) {
        TeacherID = teacherID;
        RoomData = roomData;
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
}
