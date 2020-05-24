/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import buildings.Floor;

/**
 *
 * @author votha
 */
public class PranksterTravel implements TravelStrategy{
    public PranksterTravel(){}

    @Override
    public int getDestination() {
        return 1;
    }

    @Override
    public void scheduleNextDestination(Passenger passenger, Floor currentFloor) {
        //Pranksters do not rea-appear
    }
    
}
