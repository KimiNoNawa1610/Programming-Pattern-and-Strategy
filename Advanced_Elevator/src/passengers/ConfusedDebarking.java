/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;
import logging.Logger;
import logging.StandardOutLogger;

/**
 *
 * @author votha
 */
public class ConfusedDebarking implements DebarkingStrategy{

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(elevator.getCurrentFloor().getNumber()==1){
            return true;
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()+" "+
                    passenger.getId()+" is leaving the building at floor 1");
    }
    
}
