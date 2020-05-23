/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;
import java.util.Random;
import logging.Logger;
import logging.StandardOutLogger;

/**
 *
 * @author votha
 */
public class PrankEmbarking implements EmbarkingStrategy{
    public PrankEmbarking(){}

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        int low=1;
        int high= elevator.getBuilding().getFloorCount();
        Random ran=elevator.getBuilding().getSimulation().getRandom();
        for(int count=0;count<(int)(high/2);count++){
            int Randnum=ran.nextInt(high)+low;
            while(Randnum==elevator.getCurrentFloor().getNumber()){
                Randnum=ran.nextInt(high)+low;
            }
            elevator.requestFloor(elevator.getBuilding().getFloor(Randnum));
        }
        elevator.removeObserver(passenger);
        elevator.removePassenger(passenger);
        log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.toString()+
                            passenger.getEmbarking().toString()+" request floors "+
                            " on elevator "+elevator.getNumber());
    }
    
    
    @Override
    public String toString(){
        return "Pranked";
    }
    
    
}
