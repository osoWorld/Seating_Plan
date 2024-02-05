package com.example.teacherapp.StudentPanel.classes.modelClasses;

public class StudentDetailCheckModelClass {
    private int seatImg;
    private String rollNum;
    private String seatNumber;

    public StudentDetailCheckModelClass(int seatImg, String rollNum, String seatNumber) {
        this.seatImg = seatImg;
        this.rollNum = rollNum;
        this.seatNumber = seatNumber;
    }

    public StudentDetailCheckModelClass() {
    }

    public int getSeatImg() {
        return seatImg;
    }

    public void setSeatImg(int seatImg) {
        this.seatImg = seatImg;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
