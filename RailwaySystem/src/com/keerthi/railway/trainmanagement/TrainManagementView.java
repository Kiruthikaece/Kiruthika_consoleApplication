package com.keerthi.railway.trainmanagement;

import java.util.List;
import java.util.Scanner;

import com.keerthi.railway.model.Train;

public class TrainManagementView {
    
    Scanner sc=new Scanner(System.in);
    private TrainManagementModel trainManagementModel;
    
    public TrainManagementView() {
        trainManagementModel=new TrainManagementModel(this);
    }
   

    public void addTrain() {
        Train train=new Train();
        
        System.out.println("Enter train number:");
        train.setTrainNo(sc.nextInt());
        sc.nextLine();

        System.out.println("Enter train Name:");
        train.setTrainName(sc.nextLine());

        System.out.println("Enter train departure time:");
        train.setDepartureTime(sc.nextLine());

        System.out.println("Enter train Arrival time:");
        train.setArrivalTime(sc.nextLine());
        
        System.out.println("Enter number of stopage");
        int n=sc.nextInt();
        train.setStopage(n);
        sc.nextLine();

        System.out.println("Enter train rouetes:");
        String[] routes=addRoutes(n);
        train.setRoutes(routes);

        System.out.println("Enter train fare:");
        train.setFare(sc.nextInt());

        System.out.println("Enter Total seats:");
        train.setSeats(sc.nextInt());
        sc.nextLine();

        trainManagementModel.validateTrain(train);
    }


    private String[] addRoutes(int n) {
        String[] routes=new String[n];
        for(int i=0;i<n;i++)
        routes[i]=sc.nextLine();

        return routes;
    }


    public void ListTain() {
        trainManagementModel.getAll();
    }


    public void showMessage(String message) {
       System.out.println(message);
    }


    public void showTrain(List<Train> list) {
        for(Train train:list) {
            System.out.println("-------------------------------------");
            System.out.println("Train Number:"+train.getTrainNo());
            System.out.println("Train Name:"+train.getTrainName());
            System.out.println("Train Departure Time:"+train.getDepartureTime());
            System.out.println("Train Arrival Time:"+train.getArrivalTime());
            System.out.println("Train Fare:"+train.getFare());
            System.out.println("Train Number:"+train.getSeats());
            System.out.println("Train Routes:");
            String[] routes=train.getRoutes();
            for(int i=0;i<train.getStopage();i++)
            System.out.print(routes[i]+" ");
            System.out.println("-------------------------------------");
        }
    }


}
