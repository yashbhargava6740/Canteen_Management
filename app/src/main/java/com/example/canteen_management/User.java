package com.example.canteen_management;

public class User {
    // Variables
    private String userId;
    private String userPass;
    private String userPhone;
    private String userEmail;
    private String userName;

    // Getters and setters
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return userId;}
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public String getUserEmail() { return userEmail; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
    public String getUserPhone() { return userPhone; }
    public void setUserPass(String userPass) { this.userPass = userPass; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserName() { return userName; }
}
