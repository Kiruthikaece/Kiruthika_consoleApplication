package com.keerthi.railway.reservationSystem;

import java.util.List;
import java.util.Scanner;

import com.keerthi.railway.model.Passanger;
import com.keerthi.railway.model.Train;

public class ReservationView {

    Scanner sc=new Scanner(System.in);

    private ReservationModel reservationModel;
    
    public ReservationView() {
        reservationModel=new ReservationModel(this);
    }

    public void booking() {

        System.out.println("Enter from station:");
        String fromStation=sc.nextLine();

        System.out.println("Enter To station:");
        String ToStation=sc.nextLine();

        reservationModel.getTrains(fromStation,ToStation);
        }


    public void showMessage(String message) {
       System.out.println(message);
    }

    public void getPnrStatus() {
       System.out.println("Enter PNR number:");
       reservationModel.getPnrDetails(sc.nextInt());
    }

    public void showBookingPassanger() {
        reservationModel.getAllBooking();
    }

    

    public void PrintTrains(List<Train> trainsList) {
        System.out.println("--------  Avilable Train  --------------");
        for(Train train:trainsList) {
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
            System.out.println("\n-------------------------------------");
        }
        bookPassanger();
    }

    public void bookPassanger() {
       
        System.out.println("Enter train number:");
        int trainNo=sc.nextInt();
        int pnr=(int) (Math.random()*(20000-10000+1)+1000);
        addpassangers(trainNo,pnr); 
               
    }

    private void addpassangers(int trainNo,int pnr) {
      
            Passanger passanger=new Passanger();
            sc.nextLine();
            System.out.println("Enter name:");
            passanger.setName(sc.nextLine());
            System.out.println("Enter age:");
            passanger.setAge(sc.nextInt());
            sc.nextLine();
            System.out.println("Enter gender:");
            passanger.setGender(sc.nextLine());
            passanger.setTrainNo(trainNo); 
            passanger.setPnr(pnr);
            reservationModel.validatePassanger(passanger);
            addMorePassanger(trainNo,pnr);
    }

    private void addMorePassanger(int trainNo,int pnr) {
        System.out.println("Do you want add more passanger \n type yes or no");
        String choice=sc.next();
        if(choice.equalsIgnoreCase("yes"))
        addpassangers(trainNo,pnr);
        else {
            return;
        }
       
    }

    public void printPassanger(List<Passanger> passangerList) {
        System.out.println("Name"+"  "+"Age"+" "+"Gender"+" "+"Train id"+" "+"Status");
        for(Passanger passanger:passangerList)
        System.out.println(passanger.getName()+" "+passanger.getAge()+" "+passanger.getGender()+" "+passanger.getTrainNo()+" "+passanger.getStatus());
    }

    public void cancelTicket() {
       System.out.println("Enter PNR number");
       reservationModel.isValidPnr(sc.nextInt());
    }

    public void cancelPnr(List<Passanger> pnrPassanger) {
       System.out.println("Do you want to cancel the ticket");
       reservationModel.cnfCancelTicket(sc.next(),pnrPassanger);
    }

    public void search() {
       System.out.println("Enter passanger search id:");
       reservationModel.searchPassanger(sc.nextInt());
    }

    public void changeStatus() {
       System.out.println("Enter PNR number:");
       int pnr=sc.nextInt();
       System.out.println("Enter status: \n1-CNF \n2-Cancel");
       int status=sc.nextInt();
       reservationModel.modifyStatus(pnr,status);
    }

   
    
    
}
