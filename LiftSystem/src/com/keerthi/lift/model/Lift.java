package com.keerthi.lift.model;

public class Lift {
    private int current_floor;
    private int id;
    private int capacity;

    public Lift(int id,int current_floor,int capacity) { 
        this.id=id;
        this.current_floor=current_floor;
        this.capacity=capacity;
    }

    public int getCurrent_floor() {
        return current_floor;
    }

    public void setCurrent_floor(int current_floor) {
        this.current_floor = current_floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

   
}
