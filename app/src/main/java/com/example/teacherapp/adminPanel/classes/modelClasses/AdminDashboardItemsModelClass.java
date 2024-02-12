package com.example.teacherapp.adminPanel.classes.modelClasses;

public class AdminDashboardItemsModelClass {
    private int itemIcon;
    private String itemName;
    private int itemPosition;

    public AdminDashboardItemsModelClass(int itemIcon, String itemName, int itemPosition) {
        this.itemIcon = itemIcon;
        this.itemName = itemName;
        this.itemPosition = itemPosition;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }
}
