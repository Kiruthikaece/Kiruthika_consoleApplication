package com.keerthi.railway.model;

public class Train {
    
    private int trainNo;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private String[] routes;
    private int seats;
    private int stopage;
    
   
    public int getStopage() {
        return stopage;
    }
    public void setStopage(int stopage) {
        this.stopage = stopage;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    private int fare;
    
    public int getFare() {
        return fare;
    }
    public void setFare(int fare) {
        this.fare = fare;
    }
    public void setRoutes(String[] routes) {
        this.routes = routes;
    }
    public int getTrainNo() {
        return trainNo;
    }
    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }
    public String getTrainName() {
        return trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String[] getRoutes() {
        return routes;
    }
   
   
}
