package com.example.teacherapp.teacherPanel.classes.modelClasses;

public class StudentAttendanceModelClass {
    String userId, userName, userEmail, studentDepartment, imageUrl, uid, studentAttendanceStatus;

    public StudentAttendanceModelClass(String userId, String userName, String userEmail, String studentDepartment, String imageUrl, String uid, String studentAttendanceStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.studentDepartment = studentDepartment;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.studentAttendanceStatus = studentAttendanceStatus;
    }

    public StudentAttendanceModelClass() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStudentAttendanceStatus() {
        return studentAttendanceStatus;
    }

    public void setStudentAttendanceStatus(String studentAttendanceStatus) {
        this.studentAttendanceStatus = studentAttendanceStatus;
    }

}
