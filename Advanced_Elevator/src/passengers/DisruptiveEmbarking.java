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
public class DisruptiveEmbarking implements EmbarkingStrategy{

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        if(passenger.getDestination()>elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<elevator.getBuilding().getFloorCount()-elevator.getCurrentFloor().getNumber();i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getDestination()+i));
        }
        }
        else if(passenger.getDestination()<elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<elevator.getCurrentFloor().getNumber();i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getDestination()-i));
        }
        }
    }
    
}
