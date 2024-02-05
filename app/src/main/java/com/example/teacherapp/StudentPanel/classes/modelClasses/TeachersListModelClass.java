package com.example.teacherapp.StudentPanel.classes.modelClasses;

public class TeachersListModelClass {
    private int teacherImg;
    private String teacherName;

    public TeachersListModelClass(int teacherImg, String teacherName) {
        this.teacherImg = teacherImg;
        this.teacherName = teacherName;
    }

    public TeachersListModelClass() {
    }

    public int getTeacherImg() {
        return teacherImg;
    }

    public void setTeacherImg(int teacherImg) {
        this.teacherImg = teacherImg;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
