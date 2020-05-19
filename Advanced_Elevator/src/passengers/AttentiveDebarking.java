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
public class AttentiveDebarking implements DebarkingStrategy{
    public AttentiveDebarking(){}

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(passenger.getTravel().getDestination()==elevator.getCurrentFloor().getNumber()){
            return true;
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()
                    +" "+passenger.getId()+" debarked at their destination "+elevator.getCurrentFloor().getNumber());
        passenger.getTravel().scheduleNextDestination(passenger, elevator.getCurrentFloor());
    }
    
}
