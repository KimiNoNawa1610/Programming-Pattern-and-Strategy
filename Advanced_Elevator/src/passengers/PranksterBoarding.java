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
public class PranksterBoarding implements BoardingStrategy{
    public PranksterBoarding(){}

    @Override
    public boolean willBoardElevator(Passenger passenger, Elevator elevator) {
        System.out.println("Nice");
        return true;
    }
    
}
