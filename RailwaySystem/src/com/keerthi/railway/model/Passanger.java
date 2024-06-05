package com.keerthi.railway.model;

public class Passanger {
    
    private int id;
    private String name;
    private int age;
    private String gender;
    private int trainNo;
    private int pnr;
    private String status;
    private int seat_no;

    public int getSeat_no() {
        return seat_no;
    }
    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTrainNo() {
        return trainNo;
    }
    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }
    private String Status;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }

    public int getPnr() {
        return pnr;
    }
    public void setPnr(int pnr) {
        this.pnr = pnr;
    }
}
