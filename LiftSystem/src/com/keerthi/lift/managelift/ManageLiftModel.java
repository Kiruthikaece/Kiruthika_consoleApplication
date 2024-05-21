package com.keerthi.lift.managelift;

import com.keerthi.lift.datalayer.LiftDb;

public class ManageLiftModel {

    private ManageLiftView manageLiftView;

    public ManageLiftModel(ManageLiftView manageLiftView) {
        this.manageLiftView=manageLiftView;
    }

    public void getAllLift() {
        manageLiftView.displayAllLift(LiftDb.getInstance().getAllLift());
    }

    public void setupProcess(int liftId) {
       LiftDb.getInstance().setupMaintanceLift(liftId);
       manageLiftView.showMessage("Maintance underProcess");
    }

    public void setUpLift(int currentFloor, int destinationFloor, int passengers) {
        if(LiftDb.getInstance().assignPassangerLift(currentFloor,destinationFloor,passengers))
        manageLiftView.showMessage("Assigned successfully");
        else
        manageLiftView.showMessage("Lift not available");
    }
    
}
