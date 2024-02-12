package com.example.teacherapp.adminPanel.classes.modelClasses;

public class TeacherStudentListModelClass {
    private int teacherStudentImg;
    private String teacherStudentName, teacherStudentId, teacherStudentRoom;

    public TeacherStudentListModelClass(int teacherStudentImg, String teacherStudentName, String teacherStudentId, String teacherStudentRoom) {
        this.teacherStudentImg = teacherStudentImg;
        this.teacherStudentName = teacherStudentName;
        this.teacherStudentId = teacherStudentId;
        this.teacherStudentRoom = teacherStudentRoom;
    }

    public int getTeacherStudentImg() {
        return teacherStudentImg;
    }

    public void setTeacherStudentImg(int teacherStudentImg) {
        this.teacherStudentImg = teacherStudentImg;
    }

    public String getTeacherStudentName() {
        return teacherStudentName;
    }

    public void setTeacherStudentName(String teacherStudentName) {
        this.teacherStudentName = teacherStudentName;
    }

    public String getTeacherStudentId() {
        return teacherStudentId;
    }

    public void setTeacherStudentId(String teacherStudentId) {
        this.teacherStudentId = teacherStudentId;
    }

    public String getTeacherStudentRoom() {
        return teacherStudentRoom;
    }

    public void setTeacherStudentRoom(String teacherStudentRoom) {
        this.teacherStudentRoom = teacherStudentRoom;
    }
}
