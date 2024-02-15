package com.example.teacherapp.adminPanel.classes.modelClasses;

public class AssignRoomModelClass {
    private int roomImg;
    private String roomName;

    public AssignRoomModelClass(int roomImg, String roomName) {
        this.roomImg = roomImg;
        this.roomName = roomName;
    }

    public int getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(int roomImg) {
        this.roomImg = roomImg;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
