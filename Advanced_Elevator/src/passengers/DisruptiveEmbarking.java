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
public class DisruptiveEmbarking implements EmbarkingStrategy{
    public DisruptiveEmbarking(){}
    
    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        if(passenger.getTravel().getDestination()>elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<elevator.getBuilding().getFloorCount()-passenger.getTravel().getDestination()+1;i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()+i));
                
        }
            
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            
                    passenger.getEmbarking().toString()+" request floor "+passenger.getTravel().getDestination()+
                            
                    " and everything above it on elevator "+elevator.getNumber());
        }
        else if(passenger.getTravel().getDestination()<elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<passenger.getTravel().getDestination();i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()-i));
                
        }
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            
                    passenger.getEmbarking().toString()+" request floor "+passenger.getTravel().getDestination()+
                            
                    " and everything below it on elevator "+elevator.getNumber());
        }
    }
    @Override
    public String toString(){
        return "Disruptively";
    }
    
}
