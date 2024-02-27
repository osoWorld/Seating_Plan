package com.example.teacherapp.adminPanel.classes.modelClasses;

import com.example.teacherapp.teacherPanel.classes.modelClasses.SelectedItemModel;

public class TeacherStudentListModelClass {
    String userId, userName, userPassword, userEmail, currentStatus, studentDepartment, imageUrl, uid,seatingStatus ;
    Boolean isSelected;


    public TeacherStudentListModelClass() {
    }

    public TeacherStudentListModelClass(String userId, String userName, String userPassword, String userEmail, String currentStatus, String studentDepartment, String imageUrl, String uid, String seatingStatus, Boolean isSelected) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.currentStatus = currentStatus;
        this.studentDepartment = studentDepartment;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.seatingStatus = seatingStatus;
        this.isSelected = isSelected;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
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



    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSeatingStatus() {
        return seatingStatus;
    }

    public void setSeatingStatus(String seatingStatus) {
        this.seatingStatus = seatingStatus;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getUid() {
        return uid;
    }
}
