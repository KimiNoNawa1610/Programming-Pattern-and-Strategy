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
public class JoyriderEmbarking implements EmbarkingStrategy{
    
    public JoyriderEmbarking(){}
    
    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        log.logString(passenger.getName()+" "+passenger.getId()+" Entered elevator "+elevator.getNumber()+" without requesting any floor");
    }
    
    @Override
    public String toString(){
        return "Enjoying Riding";
    }
    
}
