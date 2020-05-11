/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passengers;

import buildings.Floor;
import elevators.Simulation;
import events.PassengerNextDestinationEvent;

/**
 *
 * @author votha
 */
public class SingleDestinationTravel implements TravelStrategy{
    private int destination;
    private long schedule;
    
    public SingleDestinationTravel(int des, long sch){
        destination=des;
        schedule=sch;
    }
    @Override
    public int getDestination() {
        return destination;
    }

    @Override
    public void scheduleNextDestination(Passenger passenger, Floor currentFloor) {
        this.destination=1;
        Simulation s=currentFloor.getBuilding().getSimulation();
        PassengerNextDestinationEvent ev=new PassengerNextDestinationEvent(s.currentTime()+schedule,
                    passenger,currentFloor);
        s.scheduleEvent(ev);
        
    }
    
}
