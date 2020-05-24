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
public class ResponsibleEmbarking implements EmbarkingStrategy{

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log =new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()));
        log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            
                passenger.getEmbarking().toString()+" request floor "+passenger.getTravel().getDestination()+
                            
                " on elevator "+elevator.getNumber());
    }
    @Override
    public String toString(){
        return "Responsibly";
    }
    
}
