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
public class AwkwardBoarding implements BoardingStrategy{
    private int Threshold;
    
    public AwkwardBoarding(int threshold){
        Threshold=threshold;
    }

    @Override
    public boolean willBoardElevator(Passenger passenger, Elevator elevator) {
        if(passenger.willBoardElevator(elevator)!=true){
            Threshold*=2;
        }
        return Threshold>elevator.getPassengerCount();
        
    }
    
}
