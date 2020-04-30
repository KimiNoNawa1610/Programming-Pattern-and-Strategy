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
public class ClumsyEmbarking implements EmbarkingStrategy{

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getDestination()));
        if(passenger.getDestination()>elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getDestination()-1));
        }
        else if(passenger.getDestination()<elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getDestination()+1));
        }
        
    }
    
}
