/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;
import logging.StandardOutLogger;

/**
 *
 * @author votha
 */
public class DistractedDebarking implements DebarkingStrategy{
    int mistake=1;
    public DistractedDebarking(){}
    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(mistake==1 && elevator.getCurrentFloor().getNumber()==passenger.getTravel().getDestination()){
            mistake=0;
            return false;
            
        }
        else if(mistake ==0 && elevator.getCurrentFloor().getNumber()!=passenger.getTravel().getDestination()){
            return true;
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        //next destination of the passenger
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        if(passenger.getTravel().getDestination()==elevator.getCurrentFloor().getNumber()){
            passenger.getTravel().scheduleNextDestination(passenger, elevator.getCurrentFloor());
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()
                    +" "+passenger.getId()+" debarked at their destination "+elevator.getCurrentFloor().getNumber());
        }
        else if(passenger.getTravel().getDestination()!=elevator.getCurrentFloor().getNumber()){
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()
                    +" "+passenger.getId()+" got off elevator "+elevator.getNumber()+" on the wrong floor");
            Simulation s=elevator.getCurrentFloor().getBuilding().getSimulation();
            PassengerNextDestinationEvent ev=new PassengerNextDestinationEvent((long) (s.currentTime()+5),
                    passenger,elevator.getCurrentFloor());
            s.scheduleEvent(ev);  
        }
        
    }
    
}
