/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;
import logging.Logger;
import logging.StandardOutLogger;

/**
 *
 * @author votha
 */
public class DistractedDebarking implements DebarkingStrategy{
    int mistake=1;
    int board=1;
    public DistractedDebarking(){}
    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        if(mistake==1 && elevator.getCurrentFloor().getNumber()==passenger.getTravel().getDestination()){
            mistake=0;
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()+ " "+passenger.getId()+
                    " got distracted and missed their stop floor "+passenger.getTravel().getDestination()+" !");
            return false;
            
        }
        else if(board==1 && mistake ==0 && elevator.getCurrentFloor().getNumber()!=passenger.getTravel().getDestination()){
            board=0;
            return true;
        }
        else if(board ==0 && elevator.getCurrentFloor().getNumber()==passenger.getTravel().getDestination()){
            return true;
        }
        
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        //next destination of the passenger
        StandardOutLogger log=new StandardOutLogger(elevator.getBuilding().getSimulation());
        Logger.setInstance(log);
        if(passenger.getTravel().getDestination()==elevator.getCurrentFloor().getNumber()){
            passenger.getTravel().scheduleNextDestination(passenger, elevator.getCurrentFloor());
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()
                    +" "+passenger.getId()+" debarked at their destination "+elevator.getCurrentFloor().getNumber());
        }
        else if(passenger.getTravel().getDestination()!=elevator.getCurrentFloor().getNumber()){
            log.logString(elevator.getBuilding().getSimulation().getTime()+"s: "+passenger.getName()
                    +" "+passenger.getId()+" got off elevator "+elevator.getNumber()+" on the wrong floor!");
            Simulation s=elevator.getCurrentFloor().getBuilding().getSimulation();
            PassengerNextDestinationEvent ev=new PassengerNextDestinationEvent((long) (s.currentTime()+5),
                    passenger,elevator.getCurrentFloor());
            s.scheduleEvent(ev);  
        }
        
    }
    
}
