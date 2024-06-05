package com.keerthi.railway.reservationSystem;

import java.util.List;

import com.keerthi.railway.datalayer.FileManager;
import com.keerthi.railway.model.Passanger;
import com.keerthi.railway.model.Train;

public class ReservationModel {

    private ReservationView reservationView;

    public ReservationModel(ReservationView reservationView) {
        this.reservationView=reservationView;
    }

    public void validatePassanger(Passanger passanger) {
        if(FileManager.getInstance().addPassanger(passanger))
        reservationView.showMessage("Ticket Booked successfully your PNR number:"+passanger.getPnr());
        else
        reservationView.showMessage("Seats Not Avilable");
    }

    public void getTrains(String fromStation, String toStation) {
         List<Train> trainsList=FileManager.getInstance().getTrain(fromStation,toStation);
         if(trainsList.size()<0)
         reservationView.showMessage("Train Not avilable");
         else
         reservationView.PrintTrains(trainsList);

        }

    public void getPnrDetails(int pnr) {
       List<Passanger> list=FileManager.getInstance().getPnrPassanger(pnr);
       if(list.size()>0)
       reservationView.printPassanger(list);
       else
       reservationView.showMessage("Invalid PNR number");

    }

    public void getAllBooking() {
        List<Passanger> totalPassanger = FileManager.getInstance().getAllPassanger();
        
        if(totalPassanger.size()>0)
        reservationView.printPassanger(totalPassanger);
        else
        reservationView.showMessage("No Passangers");

   
    }

    public void isValidPnr(int pnr) {
        List<Passanger> pnrPassanger=FileManager.getInstance().isPresentPnr(pnr);
        if(pnrPassanger.size()>0)
        reservationView.cancelPnr(pnrPassanger);
        else
        reservationView.showMessage("Invalid PNR number");

    }

    public void cnfCancelTicket(String result,List<Passanger> pnrPassanger) {
       if(result.equalsIgnoreCase("yes")) {
        FileManager.getInstance().removePassanger(pnrPassanger);
        reservationView.showMessage("Ticket cancelled successfully");
       }
       else
       reservationView.showMessage("Invalid PNR number");

    }

    public void searchPassanger(int passangerId) {
        List<Passanger> passanger = FileManager.getInstance().avilablePassanger(passangerId);
        if(passanger.size()>0)
        reservationView.printPassanger(passanger);
        else
        reservationView.showMessage("Invalid passanger Id");
    }

    public void modifyStatus(int pnr, int status) {
       if(FileManager.getInstance().changePassangerStatus(pnr,status)) {
        if(status==1)
        reservationView.showMessage("Ticket status updated as confirmed");
        else
        reservationView.showMessage("Ticket status updated as cancelled");
       }
       else
       reservationView.showMessage("Invalid PNR number");
    }

    
}
