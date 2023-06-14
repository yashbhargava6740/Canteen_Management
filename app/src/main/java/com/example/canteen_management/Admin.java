package com.example.canteen_management;

public class Admin {
    private String adminId;
    private String adminName;
    private String adminPass;
    private String adminPhone;

    // Getters and setters
    public String getAdminId() { return this.adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
    public String getAdminPhone() {
        return adminPhone;
    }
    public void setAdminPhone(String adminPhone) { this.adminPhone = adminPhone; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
    public String getAdminName() { return adminName; }
}
