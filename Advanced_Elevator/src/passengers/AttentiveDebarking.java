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
public class AttentiveDebarking implements DebarkingStrategy{

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(passenger.getDestination()==elevator.getCurrentFloor().getNumber()){
            return true;
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        passenger.getTravel().scheduleNextDestination(passenger, elevator.getCurrentFloor());
    }
    
}
