/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import elevators.Elevator;

/**
 *
 * @author votha
 */
public class DistractedDebarking implements DebarkingStrategy{

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(elevator.getCurrentDirection()==Elevator.Direction.MOVING_UP){
            if(elevator.getCurrentFloor().getNumber()<passenger.getDestination()&&elevator.getCurrentState()==Elevator.ElevatorState.DOORS_OPEN){
                return true;
            }
            return false;
        }
        else if(elevator.getCurrentDirection()==Elevator.Direction.MOVING_DOWN){
            if(elevator.getCurrentFloor().getNumber()>passenger.getDestination()&&elevator.getCurrentState()==Elevator.ElevatorState.DOORS_OPEN){
                return true;
            }
            return false;
        }
        else{
            return false;
        }
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        //next destination of the passenger
        
    }
    
}
