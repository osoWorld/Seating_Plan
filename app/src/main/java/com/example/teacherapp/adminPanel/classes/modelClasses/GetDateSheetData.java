package com.example.teacherapp.adminPanel.classes.modelClasses;

public class GetDateSheetData {
    String department,examType,imageUrl;

    public GetDateSheetData() {
    }

    public GetDateSheetData(String department, String examType, String imageUrl) {
        this.department = department;
        this.examType = examType;
        this.imageUrl = imageUrl;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
