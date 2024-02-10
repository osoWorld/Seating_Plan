package com.example.teacherapp.teacherPanel.classes.modelClasses;

public class StudentAttendanceModelClass {
    private int studentImg;
    private String studentName, studentRollNumber, studentSeatNumber, studentAttendance;

    public StudentAttendanceModelClass(int studentImg, String studentName, String studentRollNumber, String studentSeatNumber, String studentAttendance) {
        this.studentImg = studentImg;
        this.studentName = studentName;
        this.studentRollNumber = studentRollNumber;
        this.studentSeatNumber = studentSeatNumber;
        this.studentAttendance = studentAttendance;
    }

    public StudentAttendanceModelClass() {
    }

    public int getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(int studentImg) {
        this.studentImg = studentImg;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRollNumber() {
        return studentRollNumber;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }

    public String getStudentSeatNumber() {
        return studentSeatNumber;
    }

    public void setStudentSeatNumber(String studentSeatNumber) {
        this.studentSeatNumber = studentSeatNumber;
    }

    public String getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(String studentAttendance) {
        this.studentAttendance = studentAttendance;
    }
}
