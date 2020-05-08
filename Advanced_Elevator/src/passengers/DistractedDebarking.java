/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;

/**
 *
 * @author votha
 */
public class DistractedDebarking implements DebarkingStrategy{
    int mistake=1;
    public DistractedDebarking(){}
    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(mistake==1){
        if(elevator.getCurrentDirection()==Elevator.Direction.MOVING_UP){
            if(elevator.getCurrentFloor().getNumber()<passenger.getTravel().getDestination()&&elevator.getCurrentState()==Elevator.ElevatorState.DOORS_OPEN){
                mistake=0;
                return true;
                
            }
            return false;
        }
        else if(elevator.getCurrentDirection()==Elevator.Direction.MOVING_DOWN){
            if(elevator.getCurrentFloor().getNumber()>passenger.getTravel().getDestination()&&elevator.getCurrentState()==Elevator.ElevatorState.DOORS_OPEN){
                mistake=0;
                return true;
            }
            return false;
        }
        else{
            return false;
        }
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        //next destination of the passenger
        if(passenger.getTravel().getDestination()==elevator.getCurrentFloor().getNumber()){
            passenger.getTravel().scheduleNextDestination(passenger, elevator.getCurrentFloor());
        }
        else if(passenger.getTravel().getDestination()!=elevator.getCurrentFloor().getNumber()){
            Simulation s=elevator.getCurrentFloor().getBuilding().getSimulation();
            PassengerNextDestinationEvent ev=new PassengerNextDestinationEvent((long) (s.currentTime()+5),
                    passenger,elevator.getCurrentFloor());
            s.scheduleEvent(ev);  
        }
        
    }
    
}
