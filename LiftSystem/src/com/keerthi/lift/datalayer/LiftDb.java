package com.keerthi.lift.datalayer;

import java.util.ArrayList;
import java.util.List;

import com.keerthi.lift.model.Lift;

public class LiftDb {
    
    List<Lift> liftList=new ArrayList<>();

    private static LiftDb liftDb;

    public static LiftDb getInstance() {
        if(liftDb==null)
        liftDb=new LiftDb();

        return liftDb;
    }

    public void setUpLift() {
        for(int i=0;i<5;i++)
        liftList.add(new Lift(i+1,0,10+i));
    }

    public List<Lift> getAllLift() {
        for(Lift x:liftList)
        System.out.println(x.getCurrent_floor()+" id is:" +x.getId());
        return liftList;
    }

    public void setupMaintanceLift(int liftId) {
        liftList.get(liftId-1).setCurrent_floor(-1);
    }

    public boolean assignPassangerLift(int currentFloor, int destinationFloor, int passengers) {
        int minStops = Integer.MAX_VALUE; 
        int assignedLiftIndex = -1; 
    
        for (int i = 0; i < 5; i++) {
            Lift currentLift = liftList.get(i);
    
            if ((currentLift.getCurrent_floor() == 0 || isnear(currentFloor, currentLift.getCurrent_floor(), destinationFloor))
                    && CheckStopage(i + 1, destinationFloor) && passengers <= currentLift.getCapacity()
                    && isValid(i)) {
               
                int stops = Math.abs(currentLift.getCurrent_floor() - currentFloor) + Math.abs(destinationFloor - currentLift.getCurrent_floor());
                
                if (stops < minStops) {
                    minStops = stops; 
                    assignedLiftIndex = i; 
                }
            }
        }

        if (assignedLiftIndex != -1) {
            Lift assignedLift = liftList.get(assignedLiftIndex);
            assignedLift.setCurrent_floor(destinationFloor);
            System.out.println("L" + (assignedLiftIndex + 1) + " is assigned to floor " + destinationFloor);
            return true;
        } else {
            return false;
        }
    
    }


    private boolean isValid(int index) {
        return liftList.get(index).getCurrent_floor()!=-1;
    }

    private boolean isnear(int current_floor, int lift_current, int designation_floor) {
        return current_floor-1==lift_current || current_floor+1==lift_current;
    }


    public boolean CheckStopage(int lift_id,int designation_floor) {
        if(lift_id==1 || lift_id==2)
        return designation_floor>=0 && designation_floor<=5;
        else if(lift_id==3 || lift_id==4)
        return (designation_floor==0 || designation_floor>=6 && designation_floor<=10);
        else
        return (designation_floor>=0 && designation_floor<=10 );
    }

}
