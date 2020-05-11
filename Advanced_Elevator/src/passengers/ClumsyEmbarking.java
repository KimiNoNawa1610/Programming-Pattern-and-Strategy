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
    public ClumsyEmbarking(){};

    @Override
    public void enteredElevator(Passenger passenger, Elevator elevator) {
        elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()));
        if(passenger.getTravel().getDestination()>elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()-1));
        }
        else if(passenger.getTravel().getDestination()<elevator.getCurrentFloor().getNumber()){
            elevator.requestFloor(elevator.getBuilding().getFloor(passenger.getTravel().getDestination()+1));
            
        }
        
    }
    @Override
    public String toString(){
        return "clumsily";
    }
    
}
