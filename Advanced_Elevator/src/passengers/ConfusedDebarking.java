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
public class ConfusedDebarking implements DebarkingStrategy{

    @Override
    public boolean willLeaveElevator(Passenger passenger, Elevator elevator) {
        if(elevator.getCurrentFloor().getNumber()==1){
            System.out.println(passenger.toString()+" is leaving the building");
            return true;
        }
        return false;
    }

    @Override
    public void departedElevator(Passenger passenger, Elevator elevator) {
        
    }
    
}
