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
public class ThresholdBoarding implements BoardingStrategy{
    private int Threshold;
    
    public ThresholdBoarding(int threshold){
        Threshold=threshold;
    }

    @Override
    public boolean willBoardElevator(Passenger passenger, Elevator elevator) {
        if((Threshold>=elevator.getPassengerCount())!=true){
            StandardOutLogger singleton=new StandardOutLogger(elevator.getBuilding().getSimulation());
            Logger.setInstance(singleton);
            singleton.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()+" "+
                    passenger.getId()+" was to awkward to board the elevator "+elevator.getNumber());
        }
        return Threshold>=elevator.getPassengerCount();
    }
    
}
