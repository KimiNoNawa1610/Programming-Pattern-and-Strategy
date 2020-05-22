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
public class PrankDebarking implements DebarkingStrategy{

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        return true;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        log.logString(" Prankster "+passenger.getId()+" is leaving the building!");
    }
    
}
