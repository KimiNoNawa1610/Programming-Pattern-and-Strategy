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
public class ClumsyEmbarking implements EmbarkingStrategy{
    public ClumsyEmbarking(){};

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()));
        if(passenger.getTravel().getDestination()>elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()-1));
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            
                    passenger.getEmbarking().toString()+" request floor "+passenger.getTravel().getDestination()+" and "
                            
                    +(passenger.getTravel().getDestination()-1)+" on elevator "+elevator.getNumber());
        }
        else if(passenger.getTravel().getDestination()<elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()+1));
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            
                    passenger.getEmbarking().toString()+" request floor "+passenger.getTravel().getDestination()+" and "
                            
                    +(passenger.getTravel().getDestination()+1)+" on elevator "+elevator.getNumber());
            
        }
        
    }
    @Override
    public String toString(){
        return "clumsily";
    }
    
}
