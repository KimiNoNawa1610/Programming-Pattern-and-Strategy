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
    public DisruptiveEmbarking(){}

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        if(passenger.getTravel().getDestination()>elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<elevator.getBuilding().getFloorCount()-elevator.getCurrentFloor().getNumber();i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()+i));
        }
        }
        else if(passenger.getTravel().getDestination()<elevator.getCurrentFloor().getNumber()){
            for( int i=0;i<elevator.getCurrentFloor().getNumber();i++){
                elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()-i));
                
        }
        }
    }
    @Override
    public String toString(){
        return "Disruptively";
    }
    
}
