package com.keerthi.interviewpanel.model;

public class Interviewer {
    private String name;
    private int id;
    private String email;
    private String userName;
    private String password;
    private boolean avilable;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Object object) {
        this.id = (int) object;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getAvilable() {
        return avilable;
    }
    public void setAvilable(boolean avilable) {
        this.avilable=avilable;
    }
}
