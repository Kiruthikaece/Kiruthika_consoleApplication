package com.keerthi.lift.managelift;

import java.util.List;
import java.util.Scanner;

import com.keerthi.lift.model.Lift;

public class ManageLiftView {
    
    private ManageLiftModel manageLiftModel;

    Scanner sc=new Scanner(System.in);

    public ManageLiftView() {
        manageLiftModel=new ManageLiftModel(this);
    }

    public void display() {
        manageLiftModel.getAllLift();
    }

    public void displayAllLift(List<Lift> lift) {
        System.out.println("------------Lift Position ----------");
        System.out.println("Lift:"+" "+"L1"+" "+"L2"+" "+"L3"+" "+"L4"+" "+"L5");
        System.out.print("FLoor: ");
        for(int i=0;i<5;i++)
        System.out.print(lift.get(i).getCurrent_floor()+"  ");
    }

    public void assignLift() {
        System.out.println("Enter current floor:");
        int currentFloor = sc.nextInt();
        System.out.println("Enter Destination floor:");
        int destinationFloor = sc.nextInt();
        System.out.println("Enter passengers:");
        int passengers = sc.nextInt();
        manageLiftModel.setUpLift(currentFloor,destinationFloor,passengers);
    }

    public void setMaintance() {
        System.out.println("Enter the lift no:");
        manageLiftModel.setupProcess(sc.nextInt());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    
}
